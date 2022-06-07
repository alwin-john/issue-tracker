FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
COPY build/libs/issue-tracker-api-0.0.1-SNAPSHOT.jar issue-tracker-api.jar
EXPOSE 8080
RUN bash -c 'touch /issue-tracker-api.jar'
ENTRYPOINT ["java","-jar","/issue-tracker-api.jar"]
