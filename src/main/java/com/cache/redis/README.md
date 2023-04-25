
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