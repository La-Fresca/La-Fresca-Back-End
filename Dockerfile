FROM bellsoft/liberica-openjdk-alpine:21 AS builder

WORKDIR /home/app
ADD ./ /home/app/la-fresca

# Install dos2unix to handle Windows line endings
RUN apk add --no-cache dos2unix && \
    dos2unix /home/app/la-fresca/mvnw && \
    chmod +x /home/app/la-fresca/mvnw
RUN cd la-fresca && ./mvnw -Dmaven.test.skip=true clean package


FROM bellsoft/liberica-openjre-alpine:21

WORKDIR /home/app
RUN apk add --no-cache tzdata && \
    cp /usr/share/zoneinfo/Asia/Colombo /etc/localtime && \
    echo "Asia/Colombo" > /etc/timezone && \
    apk del tzdata

EXPOSE 8080
SHELL ["/bin/sh", "-c"]
ENTRYPOINT java -jar ./la-fresca.jar --DATABASE_URL=$DATABASE_URL
COPY --from=builder /home/app/la-fresca/target/*.jar la-fresca.jar
