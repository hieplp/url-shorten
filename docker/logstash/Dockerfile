ARG ELASTIC_VERSION

# https://www.docker.elastic.co/
FROM docker.elastic.co/logstash/logstash:${ELASTIC_VERSION}

COPY ./mariadb-java-client-2.4.4.jar /usr/share/logstash/logstash-core/lib/jars/mariadb-java-client-2.4.4.jar

# Add your logstash plugins setup here
# Example: RUN logstash-plugin install logstash-filter-json
