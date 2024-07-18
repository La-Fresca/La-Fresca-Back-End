FROM bellsoft/liberica-openjdk-alpine:21 AS builder

WORKDIR /home/app
ADD ./ /home/app/la-fresca
RUN chmod +x /home/app/la-fresca/mvnw
RUN cd la-fresca && ./mvnw -Dmaven.test.skip=true clean package


FROM bellsoft/liberica-openjdk-alpine:21

WORKDIR /home/app
EXPOSE 8080
SHELL ["/bin/sh", "-c"]
ENTRYPOINT java -jar ./la-fresca.jar --DATABASE_URL=$DATABASE_URL
COPY --from=builder /home/app/la-fresca/target/*.jar la-fresca.jar
