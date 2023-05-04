./gradlew clean
./gradlew shadowJar
java -jar  build/libs/auth-services-1.0.jar -conf build/resources/main/config.json