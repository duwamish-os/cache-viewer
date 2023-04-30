import redis

## config
master_name = 'redis_ring'
sentinels = [
    ('localhost', 26379),
    ('localhost', 26380)
]

##
sentinel = redis.sentinel.Sentinel(sentinels)
master_ip, master_port = sentinel.discover_master(master_name)

# master-ip private ip is not reachable from localhost
# 172.25.0.11:6379. Operation timed out.
client = redis.Redis(host='localhost', port=master_port)

client.set('sponsored_ad', 'recommendations')
print(client.get('sponsored_ad'))

client.close()
