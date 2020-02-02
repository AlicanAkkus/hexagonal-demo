FROM gcr.io/distroless/java:11
LABEL maintainer=io.craftbase

COPY build/libs/restaurant-api.jar lib/restaurant-api.jar

CMD ["-Djava.security.egd=file:/dev/./urandom", "-Duser.timezone=Europe/Istanbul", "-Dfile.encoding=UTF-8", "lib/restaurant-api.jar"]
