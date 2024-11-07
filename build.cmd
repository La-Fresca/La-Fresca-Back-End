@echo off
REM Build the project and skip tests
mvn clean package -DskipTests

REM Check if the build was successful
IF ERRORLEVEL 1 (
    echo Build failed. Exiting...
    exit /b 1
)

REM Set the JAR file name
SET JAR_FILE=target\la-fresca-backend-0.0.1-SNAPSHOT.jar

REM Check if the JAR file exists
IF NOT EXIST "%JAR_FILE%" (
    echo JAR file not found. Exiting...
    exit /b 1
)
