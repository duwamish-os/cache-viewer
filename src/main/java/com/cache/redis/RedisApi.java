package com.cache.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisApi {

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
            String hashKey = "key-" + i;
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

    public List<String> getAllHashKeys(String key) {
        System.out.println("============== All hash keys ======================");
        System.out.println(String.format("============== %s ======================", key));
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();

        Map<Object, Object> tempKeys = hashOps.entries(key);
        List<String> hks = new ArrayList<>();

        for (Entry<Object, Object> entry: tempKeys.entrySet()) {
            Object hk = entry.getKey();
            hks.add((String) hk);
            System.out.println(hk);
        }

        System.out.println(key + " :: cache entry size: " + tempKeys.size());

        return hks;
    }

}
