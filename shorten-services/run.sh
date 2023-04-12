./gradlew clean
./gradlew shadowJar
java -jar  build/libs/shorten-services-1.0.jar -conf build/resources/main/config.json