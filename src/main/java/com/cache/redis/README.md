
```
docker-compose up redis
```

verify connection 

Follower
----

```bash
root@8cfc574624ae:/data# cat /etc/resolv.conf 
nameserver 127.0.0.11
options ndots:0
```

```bash
## from follower nodes
$ nmap -p 6379 127.0.0.1

Starting Nmap 6.40 ( http://nmap.org ) at 2023-04-25 20:53 UTC
Nmap scan report for localhost (127.0.0.1)
Host is up (0.00018s latency).
PORT     STATE SERVICE
6379/tcp open  unknown

Nmap done: 1 IP address (1 host up) scanned in 0.12 seconds
```


Sentinel 1
--

```bash
root@1f6a133cce9d:/data# cat /etc/resolv.conf 
nameserver 127.0.0.11
options ndots:0
```

```bash
$ nmap -p 26379 127.0.0.1
Starting Nmap 7.92 ( https://nmap.org ) at 2023-04-25 14:44 PDT
Nmap scan report for localhost (127.0.0.1)
Host is up (0.00025s latency).

PORT      STATE  SERVICE
26379/tcp closed unknown

Nmap done: 1 IP address (1 host up) scanned in 0.06 seconds
```

Sentinel 2
----------

```bash
nmap -p 26380 127.0.0.1
Starting Nmap 7.92 ( https://nmap.org ) at 2023-04-25 14:44 PDT
Nmap scan report for localhost (127.0.0.1)
Host is up (0.00023s latency).

PORT      STATE  SERVICE
26380/tcp closed unknown

Nmap done: 1 IP address (1 host up) scanned in 0.06 seconds
```

Pause leader
--

```bash
$ docker pause 9ff6a3ca3712
9ff6a3ca3712
```


```bash
docker network inspect cache-vista_vpnet 
[
    {
        "Name": "cache-vista_vpnet",
        "Id": "f20a896288e6be0421e25a45fa923ded12c1572c1942ccec45e5ebd54f1519d5",
        "Created": "2023-04-25T23:26:11.032497611Z",
        "Scope": "local",
        "Driver": "bridge",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": null,
            "Config": [
                {
                    "Subnet": "172.25.0.0/16",
                    "Gateway": "172.25.0.1"
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Ingress": false,
        "ConfigFrom": {
            "Network": ""
        },
        "ConfigOnly": false,
        "Containers": {
            "12e28fe5a4aa71030fb282db9ef428c7dbb14b3d7ec69b4987fcc20c2f499e5f": {
                "Name": "redis-master",
                "EndpointID": "fb600b925ba5261a70413e02edeccffde802dfcd57ae396035eaa54e6a0ebecb",
                "MacAddress": "02:42:ac:19:00:0b",
                "IPv4Address": "172.25.0.11/16",
                "IPv6Address": ""
            },
            "13c5b95f106c109c41c46303fbd9416ede6aed9042ab8efe4ce12edf2b37977a": {
                "Name": "cache-vista-cache-vista-app-1",
                "EndpointID": "e0fb2160d326a95cab85ba914d16f42e7cd585a2ac1dd0684705345000651f5e",
                "MacAddress": "02:42:ac:19:00:0f",
                "IPv4Address": "172.25.0.15/16",
                "IPv6Address": ""
            },
            "804bfd55377314336e5cb816f45493de18f1c6cb54eb2ca9d5ef4e00a035bdd1": {
                "Name": "redis-sentinel2",
                "EndpointID": "bf5dfcfb04f7a6f71ca76795972241f2306b3629d004f04c5a35e843b863c7ef",
                "MacAddress": "02:42:ac:19:00:0e",
                "IPv4Address": "172.25.0.14/16",
                "IPv6Address": ""
            },
            "9310cd69fe0187161f3b48554fedf98728a68669c21572544997375f8d4f133d": {
                "Name": "redis-sentinel1",
                "EndpointID": "30f09e86fa93e9a520832d72239927f0e188bb85bbc51b9a20d44f258e0a4036",
                "MacAddress": "02:42:ac:19:00:0d",
                "IPv4Address": "172.25.0.13/16",
                "IPv6Address": ""
            },
            "d3d48e58543b910340964eb5a70198847fc89f72da5d487e787cb44177149149": {
                "Name": "redis-slave",
                "EndpointID": "9d1900ac4509717469830db427da5339fb6614a31e3a9e8d79c3881839db7b14",
                "MacAddress": "02:42:ac:19:00:0c",
                "IPv4Address": "172.25.0.12/16",
                "IPv6Address": ""
            }
        },
        "Options": {},
        "Labels": {
            "com.docker.compose.network": "vpnet",
            "com.docker.compose.project": "cache-vista",
            "com.docker.compose.version": "2.5.0"
        }
    }
]

```


MacOS
----

```bash
brew install redis

redis-server
88361:C 25 Apr 2023 17:03:42.203 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
88361:C 25 Apr 2023 17:03:42.203 # Redis version=7.0.11, bits=64, commit=00000000, modified=0, pid=88361, just started
88361:C 25 Apr 2023 17:03:42.203 # Warning: no config file specified, using the default config. In order to specify a config file use redis-server /path/to/redis.conf
88361:M 25 Apr 2023 17:03:42.203 * monotonic clock: POSIX clock_gettime
                _._                                                  
           _.-``__ ''-._                                             
      _.-``    `.  `_.  ''-._           Redis 7.0.11 (00000000/0) 64 bit
  .-`` .-```.  ```\/    _.,_ ''-._                                  
 (    '      ,       .-`  | `,    )     Running in standalone mode
 |`-._`-...-` __...-.``-._|'` _.-'|     Port: 6379
 |    `-._   `._    /     _.-'    |     PID: 88361
  `-._    `-._  `-./  _.-'    _.-'                                   
 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
 |    `-._`-._        _.-'_.-'    |           https://redis.io       
  `-._    `-._`-.__.-'_.-'    _.-'                                   
 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
 |    `-._`-._        _.-'_.-'    |                                  
  `-._    `-._`-.__.-'_.-'    _.-'                                   
      `-._    `-.__.-'    _.-'                                       
          `-._        _.-'                                           
              `-.__.-'                                               

88361:M 25 Apr 2023 17:03:42.205 # Server initialized
88361:M 25 Apr 2023 17:03:42.205 * Ready to accept connections
```


```bash
redis-server --port 6380 --slaveof 127.0.0.1  6379
88605:C 25 Apr 2023 17:04:15.658 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
88605:C 25 Apr 2023 17:04:15.658 # Redis version=7.0.11, bits=64, commit=00000000, modified=0, pid=88605, just started
88605:C 25 Apr 2023 17:04:15.658 # Configuration loaded
88605:S 25 Apr 2023 17:04:15.659 * monotonic clock: POSIX clock_gettime
                _._                                                  
           _.-``__ ''-._                                             
      _.-``    `.  `_.  ''-._           Redis 7.0.11 (00000000/0) 64 bit
  .-`` .-```.  ```\/    _.,_ ''-._                                  
 (    '      ,       .-`  | `,    )     Running in standalone mode
 |`-._`-...-` __...-.``-._|'` _.-'|     Port: 6380
 |    `-._   `._    /     _.-'    |     PID: 88605
  `-._    `-._  `-./  _.-'    _.-'                                   
 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
 |    `-._`-._        _.-'_.-'    |           https://redis.io       
  `-._    `-._`-.__.-'_.-'    _.-'                                   
 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
 |    `-._`-._        _.-'_.-'    |                                  
  `-._    `-._`-.__.-'_.-'    _.-'                                   
      `-._    `-.__.-'    _.-'                                       
          `-._        _.-'                                           
              `-.__.-'                                               

88605:S 25 Apr 2023 17:04:15.660 # Server initialized
88605:S 25 Apr 2023 17:04:15.661 * Ready to accept connections
88605:S 25 Apr 2023 17:04:15.661 * Connecting to MASTER 127.0.0.1:6379
88605:S 25 Apr 2023 17:04:15.661 * MASTER <-> REPLICA sync started
88605:S 25 Apr 2023 17:04:15.661 * Non blocking connect for SYNC fired the event.
88605:S 25 Apr 2023 17:04:15.661 * Master replied to PING, replication can continue...
88605:S 25 Apr 2023 17:04:15.662 * Partial resynchronization not possible (no cached master)
88605:S 25 Apr 2023 17:04:20.470 * Full resync from master: d9d6f6b9210bdc94a51c1108c40bdb92e2d1a249:0
88605:S 25 Apr 2023 17:04:20.471 * MASTER <-> REPLICA sync: receiving streamed RDB from master with EOF to disk
88605:S 25 Apr 2023 17:04:20.471 * MASTER <-> REPLICA sync: Flushing old data
88605:S 25 Apr 2023 17:04:20.471 * MASTER <-> REPLICA sync: Loading DB in memory
88605:S 25 Apr 2023 17:04:20.494 * Loading RDB produced by version 7.0.11
88605:S 25 Apr 2023 17:04:20.494 * RDB age 0 seconds
88605:S 25 Apr 2023 17:04:20.494 * RDB memory usage when created 1.12 Mb
88605:S 25 Apr 2023 17:04:20.494 * Done loading RDB, keys loaded: 0, keys expired: 0.
88605:S 25 Apr 2023 17:04:20.494 * MASTER <-> REPLICA sync: Finished with success
```

```bash
redis-sentinel redis-sentinel/sentinel-local1.conf
89071:X 25 Apr 2023 17:05:02.672 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
89071:X 25 Apr 2023 17:05:02.672 # Redis version=7.0.11, bits=64, commit=00000000, modified=0, pid=89071, just started
89071:X 25 Apr 2023 17:05:02.672 # Configuration loaded
89071:X 25 Apr 2023 17:05:02.672 * monotonic clock: POSIX clock_gettime
                _._                                                  
           _.-``__ ''-._                                             
      _.-``    `.  `_.  ''-._           Redis 7.0.11 (00000000/0) 64 bit
  .-`` .-```.  ```\/    _.,_ ''-._                                  
 (    '      ,       .-`  | `,    )     Running in sentinel mode
 |`-._`-...-` __...-.``-._|'` _.-'|     Port: 26379
 |    `-._   `._    /     _.-'    |     PID: 89071
  `-._    `-._  `-./  _.-'    _.-'                                   
 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
 |    `-._`-._        _.-'_.-'    |           https://redis.io       
  `-._    `-._`-.__.-'_.-'    _.-'                                   
 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
 |    `-._`-._        _.-'_.-'    |                                  
  `-._    `-._`-.__.-'_.-'    _.-'                                   
      `-._    `-.__.-'    _.-'                                       
          `-._        _.-'                                           
              `-.__.-'                                               

89071:X 25 Apr 2023 17:05:02.697 * Sentinel new configuration saved on disk
89071:X 25 Apr 2023 17:05:02.697 # Sentinel ID is 20e95770c6f49cc01487f41bb86d09d04cc51194
89071:X 25 Apr 2023 17:05:02.697 # +monitor master redis_ring 127.0.0.1 6379 quorum 2
89071:X 25 Apr 2023 17:05:02.698 * +slave slave 127.0.0.1:6380 127.0.0.1 6380 @ redis_ring 127.0.0.1 6379
89071:X 25 Apr 2023 17:05:02.719 * Sentinel new configuration saved on disk


redis-sentinel redis-sentinel/sentinel-local2.conf
redis-sentinel redis-sentinel/sentinel-local3.conf
```