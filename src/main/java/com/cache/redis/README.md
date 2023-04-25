
```
docker-compose up redis
```

verify connection 

Follower
----

```bash
root@42aaf4055437:/home# cat /etc/resolv.conf 
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


Sentinels
--

```bash
$ nmap -p 26379 127.0.0.1
Starting Nmap 7.92 ( https://nmap.org ) at 2023-04-25 14:44 PDT
Nmap scan report for localhost (127.0.0.1)
Host is up (0.00025s latency).

PORT      STATE  SERVICE
26379/tcp closed unknown

Nmap done: 1 IP address (1 host up) scanned in 0.06 seconds
```

```bash
nmap -p 26380 127.0.0.1
Starting Nmap 7.92 ( https://nmap.org ) at 2023-04-25 14:44 PDT
Nmap scan report for localhost (127.0.0.1)
Host is up (0.00023s latency).

PORT      STATE  SERVICE
26380/tcp closed unknown

Nmap done: 1 IP address (1 host up) scanned in 0.06 seconds
```