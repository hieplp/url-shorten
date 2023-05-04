./gradlew clean
./gradlew shadowJar
java -jar  build/libs/user-services-1.0.jar -conf build/resources/main/config.json