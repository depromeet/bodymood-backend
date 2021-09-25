FROM adoptopenjdk/openjdk11:alpine-slim as BUILD
ENV HOME=/usr/app
WORKDIR $HOME
COPY . $HOME
RUN ./gradlew clean :dgdg-api:build

FROM adoptopenjdk/openjdk11:alpine-jre
ENV HOME=/usr/app
COPY --from=BUILD $HOME/dgdg-api/build/libs/dgdg-api.jar /dgdg-api.jar
EXPOSE 7000
ENTRYPOINT java -jar dgdg-api.jar