FROM java:11-jre

ENV VERTICLE_FILE build/libs/auth-services-1.0.jar

# Set the location of the verticles
ENV VERTICLE_HOME /opt/verticles

EXPOSE 8787

COPY $VERTICLE_FILE $VERTICLE_HOME/
COPY build/resources/main/config.json $VERTICLE_HOME/


WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java -Dvertx.logger-delegate-factory-class-name=io.vertx.core.logging.SLF4JLogDelegateFactory -jar api-gateway-fat.jar -cluster -ha -conf docker.json"]
