FROM maven:3.8.5-openjdk-17 AS JARBUILDER

WORKDIR build

# Copy only pom.xml first and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Now copy source
COPY src src

RUN mvn clean package -DskipTests

# above we are builing the project jar file and consuming it below to launch the application

FROM bellsoft/liberica-openjre-alpine

WORKDIR springApplication/app

COPY --from=JARBUILDER build/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]

