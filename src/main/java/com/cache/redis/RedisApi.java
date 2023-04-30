package com.cache.redis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

public class RedisApi {

    public static final String GLOBAL_KEY = "key-";
    RedisTemplate<String, Object> redisTemplate;

    public RedisApi(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addCache(String key, String hashKey, String value) {
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();
        hashOps.put(key, hashKey, value);
    }

    public String getCache(String key, String hashKey) {
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();
        return (String) hashOps.get(key, hashKey);
    }

    public List<Object> getCache(String key, Collection<Object> hashKeys) {
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();
        List<Object> objects = hashOps.multiGet(key, hashKeys);
        return objects;
    }

    public Long deleteCacheHashKey(String key, String hashKey) {
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();
        return hashOps.delete(key, hashKey);
    }

    public Boolean deleteCacheKey(String key) {
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();
        return redisTemplate.delete(key);
    }

    public void populateCache(int bound, String key, Optional<Integer> expirationMillis) {
        System.out.println(String.format("============== Adding hash keys to %s ======================", key));
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();

        for (int i = 0; i < bound; i++) {
            String hashKey = GLOBAL_KEY + i;
            String value = "value-" + i;
            hashOps.put(key, hashKey, value);
            //System.out.println(key + ": " + hashOps.get(key, hashKey));
        }

        expirationMillis.ifPresent(exp -> {
            redisTemplate.expire(key, exp, TimeUnit.MILLISECONDS);
        });
    }

    public Set<String> getAllKeys() {

        Set<String> tempKeys = redisTemplate.keys("*");

        for (String data : tempKeys) {
            System.out.println(data);
        }

        return tempKeys;
    }

    /**
     * https://redis.io/commands/sscan/
     */
    public List<String> getAllHashKeys_(String KEY) {
        System.out.println("===== getAllHashKeys_ ===========");
        List<String> keys = new ArrayList<>();

        RedisConnection redisConnection = null;
        try {
            redisConnection = redisTemplate.getConnectionFactory().getConnection();
            ScanOptions options = ScanOptions.scanOptions()
                .match("*" + KEY + "*")
                .count(100)
                .build();

            Cursor c = redisConnection.scan(options);
            while (c.hasNext()) {
                String k = new String((byte[]) c.next());
                keys.add(k);
                System.out.println(k);
            }
        } finally {
            redisConnection.close(); //Ensure closing this connection.
        }
        System.out.println("===== getAllHashKeys_ ===========");
        return keys;
    }

    public List<String> getAllHashKeys(String KEY) {
        System.out.println("============== All hash keys ======================");
        System.out.println(String.format("============== %s ======================", KEY));
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();

        Map<Object, Object> tempKeys = hashOps.entries(KEY);
        List<String> hks = new ArrayList<>();

        for (Entry<Object, Object> entry: tempKeys.entrySet()) {
            Object hk = entry.getKey();
            hks.add((String) hk);
            System.out.println(hk);
        }

        System.out.println(KEY + " :: cache entry size: " + tempKeys.size());

        return hks;
    }

    public List<String> getRandomHashKeys(String KEY, int count) {
        System.out.println("============== All hash keys ======================");
        System.out.println(String.format("============== %s ======================", KEY));
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();

        Map<Object, Object> tempKeys = hashOps.randomEntries(KEY, count);
        List<String> hks = new ArrayList<>();

        for (Entry<Object, Object> entry: tempKeys.entrySet()) {
            Object hk = entry.getKey();
            hks.add((String) hk);
            System.out.println(hk + ":" + entry.getValue());
        }

        System.out.println(KEY + " :: cache entry size: " + tempKeys.size());

        return hks;
    }

}
