
```bash
gradle clean jar
```

set cache
---------

```bash
λ java -jar build/libs/cache-vista-1.0-SNAPSHOT.jar localhost 11211 add user_session1 900 {"session_id":"d38b5370-0dda-400f-a620-5c95e1803076"}
connecting to : localhost:11211
2019-08-31 11:39:54.720 INFO net.spy.memcached.MemcachedConnection:  Setting retryQueueSize to -1
2019-08-31 11:39:54.746 INFO net.spy.memcached.MemcachedConnection:  Added {QA sa=localhost/127.0.0.1:11211, #Rops=0, #Wops=0, #iq=0, topRop=null, topWop=null, toWrite=0, interested=0} to connect queue
====================
operation add
====================
adding key: user_session1, value: {session_id:d38b5370-0dda-400f-a620-5c95e1803076}, expiration: 900
add result: true
2019-08-31 11:39:54.791 INFO net.spy.memcached.MemcachedConnection:  Shut down memcached client
```

get cache
---

```bash
λ java -jar build/libs/cache-vista-1.0-SNAPSHOT.jar localhost 11211 get user_session1
connecting to : localhost:11211
2019-08-31 11:40:13.074 INFO net.spy.memcached.MemcachedConnection:  Setting retryQueueSize to -1
2019-08-31 11:40:13.098 INFO net.spy.memcached.MemcachedConnection:  Added {QA sa=localhost/127.0.0.1:11211, #Rops=0, #Wops=0, #iq=0, topRop=null, topWop=null, toWrite=0, interested=0} to connect queue
====================
operation get
====================
getting : user_session1
get result: {session_id:d38b5370-0dda-400f-a620-5c95e1803076}
2019-08-31 11:40:13.126 INFO net.spy.memcached.MemcachedConnection:  Shut down memcached client
```

update cache
------

```bash
java -jar build/libs/cache-vista-1.0-SNAPSHOT.jar localhost 11211 set user_session1 900 {"session_id":"c357a0a0-f6f9-4c93-948a-86de22970652"}
connecting to : localhost:11211
2019-08-31 11:55:00.352 INFO net.spy.memcached.MemcachedConnection:  Setting retryQueueSize to -1
2019-08-31 11:55:00.376 INFO net.spy.memcached.MemcachedConnection:  Added {QA sa=localhost/127.0.0.1:11211, #Rops=0, #Wops=0, #iq=0, topRop=null, topWop=null, toWrite=0, interested=0} to connect queue
|=========================|
| executing operation set |
|=========================|
set cache for key user_session1
2019-08-31 11:55:00.410 INFO net.spy.memcached.MemcachedConnection:  Shut down memcached client
```

touch cache
------

```bash

```

delete cache
-------
```bash
λ java -jar build/libs/cache-vista-1.0-SNAPSHOT.jar localhost 11211 delete user_session1
connecting to : localhost:11211
2019-08-31 11:39:27.149 INFO net.spy.memcached.MemcachedConnection:  Setting retryQueueSize to -1
2019-08-31 11:39:27.172 INFO net.spy.memcached.MemcachedConnection:  Added {QA sa=localhost/127.0.0.1:11211, #Rops=0, #Wops=0, #iq=0, topRop=null, topWop=null, toWrite=0, interested=0} to connect queue
====================
operation delete
====================
deleted cache by key user_session1 : true
2019-08-31 11:39:27.201 INFO net.spy.memcached.MemcachedConnection:  Shut down memcached client
```

flush
-----
```bash
λ java -jar build/libs/cache-vista-1.0-SNAPSHOT.jar localhost 11211 flush
```