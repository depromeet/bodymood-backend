FROM adoptopenjdk/openjdk11:alpine-slim as BUILD
ENV HOME=/usr/app
WORKDIR $HOME
COPY . $HOME
ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY
RUN ./gradlew clean :dgdg-api:build

FROM adoptopenjdk/openjdk11:alpine-jre
ENV HOME=/usr/app
COPY --from=BUILD $HOME/dgdg-api/build/libs/dgdg-api.jar /dgdg-api.jar
EXPOSE 7000
ENTRYPOINT java -jar -Duser.timezone=Asia/Seoul dgdg-api.jar
