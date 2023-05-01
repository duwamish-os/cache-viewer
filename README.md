
- [Memcached](src/main/java/com/cache/memcached/README.md)
- [Redis](src/main/java/com/cache/redis/README.md)

Run everything on private container network, 

```bash
./docker-containerization.sh
Attaching to cache-vista_memcached_1, redis-master, redis-sentinel2, redis-sentinel1, redis-slave, cache-vista
redis-master       | 1:C 30 Apr 2023 23:18:44.659 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
redis-sentinel1    | 10:X 30 Apr 2023 23:18:46.171 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
redis-sentinel1    | 10:X 30 Apr 2023 23:18:46.171 # Redis version=7.0.11, bits=64, commit=00000000, modified=0, pid=10, just started
redis-sentinel1    | 10:X 30 Apr 2023 23:18:46.172 # Configuration loaded
redis-master       | 1:C 30 Apr 2023 23:18:44.662 # Redis version=7.0.11, bits=64, commit=00000000, modified=0, pid=1, just started
redis-master       | 1:C 30 Apr 2023 23:18:44.663 # Warning: no config file specified, using the default config. In order to specify a config file use redis-server /path/to/redis.conf
redis-slave        | 1:C 30 Apr 2023 23:18:46.265 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
redis-slave        | 1:C 30 Apr 2023 23:18:46.266 # Redis version=7.0.11, bits=64, commit=00000000, modified=0, pid=1, just started
redis-slave        | 1:C 30 Apr 2023 23:18:46.266 # Configuration loaded
redis-sentinel1    | 10:X 30 Apr 2023 23:18:46.173 * monotonic clock: POSIX clock_gettime
redis-sentinel2    | 10:X 30 Apr 2023 23:18:46.066 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
redis-slave        | 1:S 30 Apr 2023 23:18:46.267 * monotonic clock: POSIX clock_gettime
redis-slave        | 1:S 30 Apr 2023 23:18:46.268 * Running mode=standalone, port=6380.
redis-slave        | 1:S 30 Apr 2023 23:18:46.268 # Server initialized
redis-slave        | 1:S 30 Apr 2023 23:18:46.268 # WARNING Memory overcommit must be enabled! Without it, a background save or replication may fail under low memory condition. Being disabled, it can can also cause failures without low memory condition, see https://github.com/jemalloc/jemalloc/issues/1328. To fix this issue add 'vm.overcommit_memory = 1' to /etc/sysctl.conf and then reboot or run the command 'sysctl vm.overcommit_memory=1' for this to take effect.
redis-sentinel1    | 10:X 30 Apr 2023 23:18:46.178 * Running mode=sentinel, port=26379.
redis-sentinel2    | 10:X 30 Apr 2023 23:18:46.070 # Redis version=7.0.11, bits=64, commit=00000000, modified=0, pid=10, just started
redis-sentinel2    | 10:X 30 Apr 2023 23:18:46.071 # Configuration loaded
redis-sentinel1    | 10:X 30 Apr 2023 23:18:46.203 * Sentinel new configuration saved on disk
redis-sentinel1    | 10:X 30 Apr 2023 23:18:46.204 # Sentinel ID is 8345d6c21f44572bb4fb501670b9b1a403adbf8c
redis-sentinel1    | 10:X 30 Apr 2023 23:18:46.204 # +monitor master redis_ring 172.25.0.11 6379 quorum 2
redis-master       | 1:M 30 Apr 2023 23:18:44.667 * monotonic clock: POSIX clock_gettime
redis-slave        | 1:S 30 Apr 2023 23:18:46.270 * Ready to accept connections
redis-master       | 1:M 30 Apr 2023 23:18:44.670 * Running mode=standalone, port=6379.
redis-slave        | 1:S 30 Apr 2023 23:18:46.272 * Connecting to MASTER redis-master:6379
redis-master       | 1:M 30 Apr 2023 23:18:44.671 # Server initialized
redis-sentinel2    | 10:X 30 Apr 2023 23:18:46.072 * monotonic clock: POSIX clock_gettime
redis-slave        | 1:S 30 Apr 2023 23:18:46.275 * MASTER <-> REPLICA sync started
redis-sentinel2    | 10:X 30 Apr 2023 23:18:46.073 * Running mode=sentinel, port=26379.
redis-sentinel2    | 10:X 30 Apr 2023 23:18:46.085 * Sentinel new configuration saved on disk
redis-sentinel2    | 10:X 30 Apr 2023 23:18:46.085 # Sentinel ID is f7fbc5c31043c0d56cd9ce9da9b351e20da682e6
redis-sentinel2    | 10:X 30 Apr 2023 23:18:46.085 # +monitor master redis_ring 172.25.0.11 6379 quorum 2
redis-master       | 1:M 30 Apr 2023 23:18:44.671 # WARNING Memory overcommit must be enabled! Without it, a background save or replication may fail under low memory condition. Being disabled, it can can also cause failures without low memory condition, see https://github.com/jemalloc/jemalloc/issues/1328. To fix this issue add 'vm.overcommit_memory = 1' to /etc/sysctl.conf and then reboot or run the command 'sysctl vm.overcommit_memory=1' for this to take effect.
redis-master       | 1:M 30 Apr 2023 23:18:44.674 * Ready to accept connections
redis-slave        | 1:S 30 Apr 2023 23:18:46.275 * Non blocking connect for SYNC fired the event.
redis-master       | 1:M 30 Apr 2023 23:18:46.282 * Replica 172.25.0.12:6380 asks for synchronization
redis-master       | 1:M 30 Apr 2023 23:18:46.282 * Full resync requested by replica 172.25.0.12:6380
redis-master       | 1:M 30 Apr 2023 23:18:46.282 * Replication backlog created, my new replication IDs are '73f499187a4618c09f28ba972c5fa40f3f939490' and '0000000000000000000000000000000000000000'
redis-master       | 1:M 30 Apr 2023 23:18:46.282 * Delay next BGSAVE for diskless SYNC
redis-slave        | 1:S 30 Apr 2023 23:18:46.279 * Master replied to PING, replication can continue...
redis-slave        | 1:S 30 Apr 2023 23:18:46.282 * Partial resynchronization not possible (no cached master)
redis-sentinel1    | 10:X 30 Apr 2023 23:18:48.138 * +sentinel sentinel f7fbc5c31043c0d56cd9ce9da9b351e20da682e6 172.25.0.14 26379 @ redis_ring 172.25.0.11 6379
redis-sentinel1    | 10:X 30 Apr 2023 23:18:48.180 * Sentinel new configuration saved on disk
redis-sentinel2    | 10:X 30 Apr 2023 23:18:48.203 * +sentinel sentinel 8345d6c21f44572bb4fb501670b9b1a403adbf8c 172.25.0.13 26379 @ redis_ring 172.25.0.11 6379
redis-sentinel2    | 10:X 30 Apr 2023 23:18:48.230 * Sentinel new configuration saved on disk
cache-vista        | 
cache-vista        |   .   ____          _            __ _ _
cache-vista        |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
cache-vista        | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
cache-vista        |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
cache-vista        |   '  |____| .__|_| |_|_| |_\__, | / / / /
cache-vista        |  =========|_|==============|___/=/_/_/_/
cache-vista        |  :: Spring Boot ::               (v2.7.11)
cache-vista        | 
redis-master       | 1:M 30 Apr 2023 23:18:51.840 * Starting BGSAVE for SYNC with target: replicas sockets
redis-master       | 1:M 30 Apr 2023 23:18:51.842 * Background RDB transfer started by pid 20
redis-master       | 20:C 30 Apr 2023 23:18:51.843 * Fork CoW for RDB: current 0 MB, peak 0 MB, average 0 MB
redis-master       | 1:M 30 Apr 2023 23:18:51.846 # Diskless rdb transfer, done reading from pipe, 1 replicas still up.
redis-slave        | 1:S 30 Apr 2023 23:18:51.849 * Full resync from master: 73f499187a4618c09f28ba972c5fa40f3f939490:579
redis-slave        | 1:S 30 Apr 2023 23:18:51.852 * MASTER <-> REPLICA sync: receiving streamed RDB from master with EOF to disk
redis-slave        | 1:S 30 Apr 2023 23:18:51.853 * MASTER <-> REPLICA sync: Flushing old data
redis-slave        | 1:S 30 Apr 2023 23:18:51.853 * MASTER <-> REPLICA sync: Loading DB in memory
redis-slave        | 1:S 30 Apr 2023 23:18:51.859 * Loading RDB produced by version 7.0.11
redis-slave        | 1:S 30 Apr 2023 23:18:51.859 * RDB age 0 seconds
redis-slave        | 1:S 30 Apr 2023 23:18:51.859 * RDB memory usage when created 1.10 Mb
redis-slave        | 1:S 30 Apr 2023 23:18:51.859 * Done loading RDB, keys loaded: 0, keys expired: 0.
redis-slave        | 1:S 30 Apr 2023 23:18:51.859 * MASTER <-> REPLICA sync: Finished with success
redis-master       | 1:M 30 Apr 2023 23:18:51.861 * Background RDB transfer terminated with success
redis-master       | 1:M 30 Apr 2023 23:18:51.861 * Streamed RDB transfer with replica 172.25.0.12:6380 succeeded (socket). Waiting for REPLCONF ACK from slave to enable streaming
redis-master       | 1:M 30 Apr 2023 23:18:51.861 * Synchronization with replica 172.25.0.12:6380 succeeded
cache-vista        | WARNING: sun.reflect.Reflection.getCallerClass is not supported. This will impact performance.
cache-vista        | ============== Adding hash keys to ROOT_TABLE ======================
cache-vista        | ============== Adding hash keys to ROOT_TABLE_EXPIRABLE ======================
cache-vista        | ===================
redis-sentinel2    | 10:X 30 Apr 2023 23:18:56.127 * +slave slave 172.25.0.12:6380 172.25.0.12 6380 @ redis_ring 172.25.0.11 6379
redis-sentinel2    | 10:X 30 Apr 2023 23:18:56.141 * Sentinel new configuration saved on disk
redis-sentinel1    | 10:X 30 Apr 2023 23:18:56.276 * +slave slave 172.25.0.12:6380 172.25.0.12 6380 @ redis_ring 172.25.0.11 6379
redis-sentinel1    | 10:X 30 Apr 2023 23:18:56.288 * Sentinel new configuration saved on disk
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE: null
cache-vista        | ROOT_TABLE_EXPIRABLE
cache-vista        | ROOT_TABLE
cache-vista        | ROOT_TABLE_EXPIRABLE cache size: 2
cache-vista        | ============== All hash keys ======================
cache-vista        | ============== ROOT_TABLE ======================
cache-vista        | key-0
cache-vista        | key-1
cache-vista        | key-2
cache-vista        | key-3
cache-vista        | key-4
cache-vista        | key-5
cache-vista        | key-6
cache-vista        | key-7
cache-vista        | key-8
cache-vista        | key-9
cache-vista        | ROOT_TABLE :: cache entry size: 10
cache-vista        | ============== All hash keys ======================
cache-vista        | ============== ROOT_TABLE ======================
cache-vista        | key-0:value-0
cache-vista        | key-1:value-1
cache-vista        | key-2:value-2
cache-vista        | key-3:value-3
cache-vista        | key-4:value-4
cache-vista        | key-5:value-5
cache-vista        | key-6:value-6
cache-vista        | key-7:value-7
cache-vista        | key-8:value-8
cache-vista        | key-9:value-9
cache-vista        | ROOT_TABLE :: cache entry size: 10
cache-vista        | key-1: value-1
cache-vista        | key-3: value-3
cache-vista        | key-2: value-2
cache-vista        | DOES_NOT_EXIST_HASH: null
cache-vista        |     fire second request
cache-vista        | ============== All hash keys ======================
cache-vista        | ============== ROOT_TABLE ======================
cache-vista        | key-0
cache-vista        | key-1
cache-vista        | key-2
cache-vista        | key-3
cache-vista        | key-4
cache-vista        | key-5
cache-vista        | key-6
cache-vista        | key-7
cache-vista        | key-8
cache-vista        | key-9
cache-vista        | ROOT_TABLE :: cache entry size: 10
cache-vista        | ============== All hash keys ======================
cache-vista        | ============== ROOT_TABLE ======================
cache-vista        | key-0:value-0
cache-vista        | key-1:value-1
cache-vista        | key-2:value-2
cache-vista        | key-3:value-3
cache-vista        | key-4:value-4
cache-vista        | key-5:value-5
cache-vista        | key-6:value-6
cache-vista        | key-7:value-7
cache-vista        | key-8:value-8
cache-vista        | key-9:value-9
cache-vista        | ROOT_TABLE :: cache entry size: 10
redis-sentinel2    | 10:X 30 Apr 2023 23:19:06.264 * +fix-slave-config slave 172.25.0.12:6380 172.25.0.12 6380 @ redis_ring 172.25.0.11 6379
redis-slave        | 1:M 30 Apr 2023 23:19:06.266 # Connection with master lost.
redis-slave        | 1:M 30 Apr 2023 23:19:06.266 * Caching the disconnected master state.
redis-slave        | 1:S 30 Apr 2023 23:19:06.266 * Connecting to MASTER 172.25.0.11:6379
redis-slave        | 1:S 30 Apr 2023 23:19:06.268 * MASTER <-> REPLICA sync started
redis-master       | 1:M 30 Apr 2023 23:19:06.270 # Connection with replica 172.25.0.12:6380 lost.
redis-slave        | 1:S 30 Apr 2023 23:19:06.271 * REPLICAOF 172.25.0.11:6379 enabled (user request from 'id=4 addr=172.25.0.14:41048 laddr=172.25.0.12:6380 fd=9 name=sentinel-f7fbc5c3-cmd age=10 idle=0 flags=x db=0 sub=0 psub=0 ssub=0 multi=4 qbuf=199 qbuf-free=20275 argv-mem=4 multi-mem=179 rbs=1024 rbp=1024 obl=45 oll=0 omem=0 tot-mem=22575 events=r cmd=exec user=default redir=-1 resp=2')
redis-slave        | 1:S 30 Apr 2023 23:19:06.273 * Non blocking connect for SYNC fired the event.
redis-slave        | 1:S 30 Apr 2023 23:19:06.275 * Master replied to PING, replication can continue...
redis-master       | 1:M 30 Apr 2023 23:19:06.278 * Replica 172.25.0.12:6380 asks for synchronization
redis-master       | 1:M 30 Apr 2023 23:19:06.278 * Partial resynchronization request from 172.25.0.12:6380 accepted. Sending 0 bytes of backlog starting from offset 11152.
redis-slave        | 1:S 30 Apr 2023 23:19:06.277 * Trying a partial resynchronization (request 73f499187a4618c09f28ba972c5fa40f3f939490:11152).
redis-slave        | 1:S 30 Apr 2023 23:19:06.281 * Successful partial resynchronization with master.
redis-slave        | 1:S 30 Apr 2023 23:19:06.282 * MASTER <-> REPLICA sync: Master accepted a Partial Resynchronization.
```

Resources
--

```bash





























CONTAINER ID   NAME                      CPU %     MEM USAGE / LIMIT     MEM %     NET I/O           BLOCK I/O     PIDS
2bb924c5cfb4   cache-vista               60.82%    111.2MiB / 786MiB     14.15%    808B / 0B         0B / 98.3kB   14
6557bf18f509   redis-sentinel1           4.31%     2.777MiB / 256MiB     1.08%     97.2kB / 34.2kB   0B / 20.5kB   6
9ef24fcbdf6c   redis-slave               3.23%     2.824MiB / 256MiB     1.10%     35.5kB / 129kB    0B / 4.1kB    5
0f4baa2be7ad   redis-sentinel2           5.18%     2.797MiB / 256MiB     1.09%     97.1kB / 34.1kB   0B / 20.5kB   6
6eab611a2b6d   cache-vista-memcached-1   0.13%     1.578MiB / 3.826GiB   0.04%     1.25kB / 0B       0B / 0B       10
cf260b75aa64   redis-master              1.03%     2.727MiB / 256MiB     1.07%     28.7kB / 56.9kB   0B / 0B       5




























CONTAINER ID   NAME                      CPU %     MEM USAGE / LIMIT     MEM %     NET I/O           BLOCK I/O     PIDS
2bb924c5cfb4   cache-vista               53.45%    113.9MiB / 786MiB     14.49%    808B / 0B         0B / 98.3kB   14
6557bf18f509   redis-sentinel1           2.47%     2.777MiB / 256MiB     1.08%     97.6kB / 34.7kB   0B / 20.5kB   6
9ef24fcbdf6c   redis-slave               1.19%     2.949MiB / 256MiB     1.15%     35.9kB / 129kB    0B / 4.1kB    5
0f4baa2be7ad   redis-sentinel2           4.35%     2.793MiB / 256MiB     1.09%     103kB / 34.9kB    0B / 20.5kB   6
6eab611a2b6d   cache-vista-memcached-1   0.06%     1.578MiB / 3.826GiB   0.04%     1.25kB / 0B       0B / 0B       10
cf260b75aa64   redis-master              2.18%     2.715MiB / 256MiB     1.06%     29.7kB / 67.4kB   0B / 0B       5




























CONTAINER ID   NAME                      CPU %     MEM USAGE / LIMIT     MEM %     NET I/O           BLOCK I/O     PIDS
2bb924c5cfb4   cache-vista               53.45%    113.9MiB / 786MiB     14.49%    808B / 0B         0B / 98.3kB   14
6557bf18f509   redis-sentinel1           2.47%     2.777MiB / 256MiB     1.08%     97.6kB / 34.7kB   0B / 20.5kB   6
9ef24fcbdf6c   redis-slave               1.19%     2.949MiB / 256MiB     1.15%     35.9kB / 129kB    0B / 4.1kB    5
0f4baa2be7ad   redis-sentinel2           4.35%     2.793MiB / 256MiB     1.09%     103kB / 34.9kB    0B / 20.5kB   6
6eab611a2b6d   cache-vista-memcached-1   0.06%     1.578MiB / 3.826GiB   0.04%     1.25kB / 0B       0B / 0B       10
cf260b75aa64   redis-master              2.18%     2.715MiB / 256MiB     1.06%     29.7kB / 67.4kB   0B / 0B       5




























CONTAINER ID   NAME                      CPU %     MEM USAGE / LIMIT     MEM %     NET I/O          BLOCK I/O     PIDS
2bb924c5cfb4   cache-vista               53.45%    113.9MiB / 786MiB     14.49%    808B / 0B        0B / 98.3kB   14
6557bf18f509   redis-sentinel1           2.85%     2.777MiB / 256MiB     1.08%     105kB / 36.6kB   0B / 20.5kB   6
9ef24fcbdf6c   redis-slave               1.88%     2.82MiB / 256MiB      1.10%     37.8kB / 131kB   0B / 4.1kB    5
0f4baa2be7ad   redis-sentinel2           2.41%     2.914MiB / 256MiB     1.14%     105kB / 36.7kB   0B / 20.5kB   6
6eab611a2b6d   cache-vista-memcached-1   0.07%     1.578MiB / 3.826GiB   0.04%     1.25kB / 0B      0B / 0B       10
cf260b75aa64   redis-master              1.96%     2.723MiB / 256MiB     1.06%     31kB / 69kB      0B / 0B       5




























CONTAINER ID   NAME                      CPU %     MEM USAGE / LIMIT     MEM %     NET I/O          BLOCK I/O     PIDS
2bb924c5cfb4   cache-vista               55.79%    115.9MiB / 786MiB     14.74%    808B / 0B        0B / 98.3kB   15
6557bf18f509   redis-sentinel1           2.85%     2.777MiB / 256MiB     1.08%     105kB / 36.6kB   0B / 20.5kB   6
9ef24fcbdf6c   redis-slave               1.88%     2.82MiB / 256MiB      1.10%     37.8kB / 131kB   0B / 4.1kB    5
0f4baa2be7ad   redis-sentinel2           2.41%     2.914MiB / 256MiB     1.14%     105kB / 36.7kB   0B / 20.5kB   6
6eab611a2b6d   cache-vista-memcached-1   0.07%     1.578MiB / 3.826GiB   0.04%     1.25kB / 0B      0B / 0B       10
cf260b75aa64   redis-master              1.96%     2.723MiB / 256MiB     1.06%     31kB / 69kB      0B / 0B       5
```