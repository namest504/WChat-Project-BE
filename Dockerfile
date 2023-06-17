FROM openjdk:11

ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

COPY build/libs/*.jar application.jar

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "-Dset_port=8080", "application.jar"]