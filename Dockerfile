FROM bellsoft/liberica-runtime-container:jdk-21-stream-musl AS builder

WORKDIR /home/app
ADD ./ /home/app/la-fresca
RUN chmod +x /home/app/la-fresca/mvnw
RUN cd la-fresca && ./mvnw -Dmaven.test.skip=true clean package


FROM bellsoft/liberica-runtime-container:jre-21-musl

WORKDIR /home/app
EXPOSE 8080
SHELL ["/bin/sh", "-c"]
ENTRYPOINT java -jar ./la-fresca.jar --DATABASE_URL=$DATABASE_URL
COPY --from=builder /home/app/la-fresca/target/*.jar la-fresca.jar