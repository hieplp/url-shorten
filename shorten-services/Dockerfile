FROM openjdk:11

ENV VERTICLE_FILE build/libs/shorten-services-1.0.jar

# Set the location of the verticles
ENV VERTICLE_HOME /opt/verticles

#EXPOSE 9001

COPY $VERTICLE_FILE $VERTICLE_HOME/
#COPY build/resources/main/docker.json $VERTICLE_HOME/config.json

WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar  shorten-services-1.0.jar -conf $CONFIG_FILE"]
