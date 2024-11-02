@echo off
REM Set the DATABASE_URL environment variable
SET DATABASE_URL="mongodb+srv://thecafelafresca:RMwrw7VSCwMnwWf4@lafresca.3litabe.mongodb.net/LaFresca_DB?retryWrites=true&w=majority&appName=LaFresca"

REM Set the JAR file name
SET JAR_FILE=target\la-fresca-backend-0.0.1-SNAPSHOT.jar

REM Run the JAR file with the DATABASE_URL argument
java -jar "%JAR_FILE%" --DATABASE_URL=%DATABASE_URL%
