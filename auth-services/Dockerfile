FROM openjdk:11

ENV VERTICLE_FILE build/libs/auth-services-1.0.jar

# Set the location of the verticles
ENV VERTICLE_HOME /opt/verticles

#EXPOSE 9001

COPY $VERTICLE_FILE $VERTICLE_HOME/

WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar  auth-services-1.0.jar -conf $CONFIG_FILE"]
