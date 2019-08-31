
```bash
gradle clean jar
```

set cache
---------

```bash
java -jar build/libs/cache-service-1.0-SNAPSHOT.jar localhost 11211 add user_session1 900 {"session_id":"d38b5370-0dda-400f-a620-5c95e1803076"}
```

get cache
---

```bash
java -jar build/libs/cache-service-1.0-SNAPSHOT.jar localhost 11211 get user_session1
connecting to : localhost:11211
2019-08-31 11:27:03.034 INFO net.spy.memcached.MemcachedConnection:  Setting retryQueueSize to -1
2019-08-31 11:27:03.059 INFO net.spy.memcached.MemcachedConnection:  Added {QA sa=localhost/127.0.0.1:11211, #Rops=0, #Wops=0, #iq=0, topRop=null, topWop=null, toWrite=0, interested=0} to connect queue
====================
operation get
====================
getting : user_session1
get result: {session_id:
2019-08-31 11:27:03.089 INFO net.spy.memcached.MemcachedConnection:  Shut down memcached client
```

update
------

touch
------

delete
-------

flush
-----
