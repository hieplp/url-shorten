FROM openjdk:11

ENV VERTICLE_FILE build/libs/user-services-1.0.jar

# Set the location of the verticles
ENV VERTICLE_HOME /opt/verticles

#EXPOSE 9001

COPY $VERTICLE_FILE $VERTICLE_HOME/
COPY build/resources/main/docker.json $VERTICLE_HOME/config.json

WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar  user-services-1.0.jar -conf config.json"]
