
```bash
gradle clean jar
java -jar build/libs/cache-1.0-SNAPSHOT.jar localhost 11211 add session1 900 updupdupd
java -jar build/libs/cache-1.0-SNAPSHOT.jar localhost 11211 get session1
```
