import redis

# Create a connection to the Redis master
r = redis.Redis(host='localhost', port=6379, db=0)

r.set('product_id', '2004')
value = r.get('product_id')
print(value)
