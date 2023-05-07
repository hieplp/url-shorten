./gradlew clean
./gradlew shadowJar
java -jar  build/libs/statistic-services-1.0.jar -conf build/resources/main/config.json