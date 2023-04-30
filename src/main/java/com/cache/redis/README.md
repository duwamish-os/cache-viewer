
```bash
./gradlew clean build
docker-compose up
```

verify connection 

Slave
----

```bash
root@8cfc574624ae:/data# cat /etc/resolv.conf 
nameserver 127.0.0.11
options ndots:0
```

```bash
## from slave nodes
$ docker exec -it $(docker ps -qf name=redis-slave) bash 
root@d28a3ea55626:/data# nmap -p 6379 172.25.0.11
Starting Nmap 7.80 ( https://nmap.org ) at 2023-04-30 22:07 UTC
Nmap scan report for redis-master.cache-vista_vpnet (172.25.0.11)
Host is up (0.00031s latency).

PORT     STATE SERVICE
6379/tcp open  redis
MAC Address: 02:42:AC:19:00:0B (Unknown)

Nmap done: 1 IP address (1 host up) scanned in 0.18 seconds
```


Sentinel 1
--

```bash
$ docker exec -it $(docker ps -qf name=redis-sentinel1) bash
root@1f6a133cce9d:/data# cat /etc/resolv.conf 
nameserver 127.0.0.11
options ndots:0
```

```bash
## on sentinel
root@f83baf6686f7:/redis# nmap -p 6379 172.25.0.11
Starting Nmap 7.80 ( https://nmap.org ) at 2023-04-30 22:08 UTC
Nmap scan report for redis-master.cache-vista_vpnet (172.25.0.11)
Host is up (0.00028s latency).

PORT     STATE SERVICE
6379/tcp open  redis
MAC Address: 02:42:AC:19:00:0B (Unknown)

Nmap done: 1 IP address (1 host up) scanned in 0.19 seconds

## on macos
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
$ docker pause $(docker ps -qf name=redis-master)
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


Connect to sentinel
--

```bash
## does not work from redis-master docker
## connecting to sentinel
root@c42357b67e81:/data# redis-cli -h 172.25.0.13 -p 26379
Could not connect to Redis at 172.25.0.13:26379: Connection refused

root@c42357b67e81:/data# redis-cli -h 127.0.0.1 -p 26379
Could not connect to Redis at 127.0.0.1:26379: Connection refused

## on a host MacOS
millionaire $ redis-cli -h 127.0.0.1 -p 26379
127.0.0.1:26379> 
```


```bash
## on redis-master docker container bridge network
root@c42357b67e81:/data# redis-cli -h 127.0.0.1 -p 6379
127.0.0.1:6379> 
127.0.0.1:6379> set product_id 12345
OK
127.0.0.1:6379> get product_id
"12345"


127.0.0.1:6379> info
# Server
redis_version:7.0.11
redis_git_sha1:00000000
redis_git_dirty:0
redis_build_id:5c712dc4cb9cfb70
redis_mode:standalone
os:Linux 5.15.0-67-generic x86_64
arch_bits:64
monotonic_clock:POSIX clock_gettime
multiplexing_api:epoll
atomicvar_api:c11-builtin
gcc_version:10.2.1
process_id:1
process_supervised:no
run_id:3a3d9aedcfe02d961e0d6587702ccfc6772cd095
tcp_port:6379
server_time_usec:1682736659042526
uptime_in_seconds:81822
uptime_in_days:0
hz:10
configured_hz:10
lru_clock:5015058
executable:/data/redis-server
config_file:
io_threads_active:0

# Clients
connected_clients:1
cluster_connections:0
maxclients:10000
client_recent_max_input_buffer:20480
client_recent_max_output_buffer:20504
blocked_clients:0
tracking_clients:0
clients_in_timeout_table:0

# Memory
used_memory:1078840
used_memory_human:1.03M
used_memory_rss:9310208
used_memory_rss_human:8.88M
used_memory_peak:1206416
used_memory_peak_human:1.15M
used_memory_peak_perc:89.43%
used_memory_overhead:884580
used_memory_startup:862016
used_memory_dataset:194260
used_memory_dataset_perc:89.59%
allocator_allocated:1297880
allocator_active:1638400
allocator_resident:4493312
total_system_memory:4107751424
total_system_memory_human:3.83G
used_memory_lua:31744
used_memory_vm_eval:31744
used_memory_lua_human:31.00K
used_memory_scripts_eval:0
number_of_cached_scripts:0
number_of_functions:0
number_of_libraries:0
used_memory_vm_functions:32768
used_memory_vm_total:64512
used_memory_vm_total_human:63.00K
used_memory_functions:184
used_memory_scripts:184
used_memory_scripts_human:184B
maxmemory:0
maxmemory_human:0B
maxmemory_policy:noeviction
allocator_frag_ratio:1.26
allocator_frag_bytes:340520
allocator_rss_ratio:2.74
allocator_rss_bytes:2854912
rss_overhead_ratio:2.07
rss_overhead_bytes:4816896
mem_fragmentation_ratio:8.82
mem_fragmentation_bytes:8254256
mem_not_counted_for_evict:0
mem_replication_backlog:20508
mem_total_replication_buffers:20504
mem_clients_slaves:0
mem_clients_normal:1800
mem_cluster_links:0
mem_aof_buffer:0
mem_allocator:jemalloc-5.2.1
active_defrag_running:0
lazyfree_pending_objects:0
lazyfreed_objects:0

# Persistence
loading:0
async_loading:0
current_cow_peak:0
current_cow_size:0
current_cow_size_age:0
current_fork_perc:0.00
current_save_keys_processed:0
current_save_keys_total:0
rdb_changes_since_last_save:0
rdb_bgsave_in_progress:0
rdb_last_save_time:1682736525
rdb_last_bgsave_status:ok
rdb_last_bgsave_time_sec:1
rdb_current_bgsave_time_sec:-1
rdb_saves:1
rdb_last_cow_size:315392
rdb_last_load_keys_expired:0
rdb_last_load_keys_loaded:0
aof_enabled:0
aof_rewrite_in_progress:0
aof_rewrite_scheduled:0
aof_last_rewrite_time_sec:-1
aof_current_rewrite_time_sec:-1
aof_last_bgrewrite_status:ok
aof_rewrites:0
aof_rewrites_consecutive_failures:0
aof_last_write_status:ok
aof_last_cow_size:0
module_fork_in_progress:0
module_fork_last_cow_size:0

# Stats
total_connections_received:91
total_commands_processed:9037
instantaneous_ops_per_sec:1
total_net_input_bytes:339267
total_net_output_bytes:185811
total_net_repl_input_bytes:0
total_net_repl_output_bytes:12587
instantaneous_input_kbps:0.04
instantaneous_output_kbps:0.01
instantaneous_input_repl_kbps:0.00
instantaneous_output_repl_kbps:0.01
rejected_connections:0
sync_full:1
sync_partial_ok:88
sync_partial_err:0
expired_keys:0
expired_stale_perc:0.00
expired_time_cap_reached_count:0
expire_cycle_cpu_milliseconds:3017
evicted_keys:0
evicted_clients:0
total_eviction_exceeded_time:0
current_eviction_exceeded_time:0
keyspace_hits:1
keyspace_misses:0
pubsub_channels:0
pubsub_patterns:0
pubsubshard_channels:0
latest_fork_usec:2033
total_forks:2
migrate_cached_sockets:0
slave_expires_tracked_keys:0
active_defrag_hits:0
active_defrag_misses:0
active_defrag_key_hits:0
active_defrag_key_misses:0
total_active_defrag_time:0
current_active_defrag_time:0
tracking_total_keys:0
tracking_total_items:0
tracking_total_prefixes:0
unexpected_error_replies:0
total_error_replies:1
dump_payload_sanitizations:0
total_reads_processed:9108
total_writes_processed:1237
io_threaded_reads_processed:0
io_threaded_writes_processed:0
reply_buffer_shrinks:90
reply_buffer_expands:0

# Replication
role:master
connected_slaves:1
slave0:ip=172.25.0.12,port=6380,state=online,offset=12314,lag=1
master_failover_state:no-failover
master_replid:9c9671e8b2089472511f444121df56635416fdd4
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:12328
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:12328

# CPU
used_cpu_sys:117.401936
used_cpu_user:9.538738
used_cpu_sys_children:0.006721
used_cpu_user_children:0.008977
used_cpu_sys_main_thread:117.386718
used_cpu_user_main_thread:9.541488

# Modules

# Errorstats
errorstat_ERR:count=1

# Cluster
cluster_enabled:0

# Keyspace
db0:keys=1,expires=0,avg_ttl=0
```

```bash
## on macos
root@f83baf6686f7:/redis# redis-cli -h 172.25.0.11 ping
PONG
```