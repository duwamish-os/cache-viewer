import redis

# Create a connection to the Redis master
r = redis.Redis(host='localhost', port=6379, db=0)

# Set a value in the Redis master
r.set('product_id', '2004')

# Get the value from the Redis master
value = r.get('product_id')

# Print the value
print(value)

