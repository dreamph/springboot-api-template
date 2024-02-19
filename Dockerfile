FROM eclipse-temurin:21-jdk-alpine as build
WORKDIR /app

COPY . .

RUN ./gradlew build bootJar

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

RUN apk add --no-cache tzdata openssl
ENV TZ=Asia/Bangkok

COPY --from=build /app/build/libs/eds-api-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java", "-Xmx256m", "-Dlog4j2.formatMsgNoLookups=true", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar", "--spring.profiles.active=dev"]

EXPOSE 8080