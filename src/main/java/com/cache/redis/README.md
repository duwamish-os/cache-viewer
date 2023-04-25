
```
docker-compose up redis
```

verify connection 

```
bash-4.2# nmap -p 6379 127.0.0.1

Starting Nmap 6.40 ( http://nmap.org ) at 2023-04-25 20:53 UTC
Nmap scan report for localhost (127.0.0.1)
Host is up (0.00018s latency).
PORT     STATE SERVICE
6379/tcp open  unknown

Nmap done: 1 IP address (1 host up) scanned in 0.12 seconds
```
