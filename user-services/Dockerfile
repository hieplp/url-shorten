FROM openjdk:11

ENV VERTICLE_FILE build/libs/user-services-1.0.jar

# Set the location of the verticles
ENV VERTICLE_HOME /opt/verticles

# Set the timezone
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Copy your fat jar to the container
COPY $VERTICLE_FILE $VERTICLE_HOME/

WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar  user-services-1.0.jar -conf $CONFIG_FILE"]
