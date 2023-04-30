package com.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.util.*;

import static com.cache.redis.RedisApi.GLOBAL_KEY;

@SpringBootApplication
public class RedisSentinelApplication {

    @Autowired
    RedisTemplate<String, Object> redisRingTemplate;

    public static void main(String... args) {
        SpringApplication.run(RedisSentinelApplication.class, args);
    }

    @Bean
    RedisApi redisApi() {
        return new RedisApi(redisRingTemplate);
    }

    String rootTableExp = "ROOT_TABLE_EXPIRABLE";
    String rootTable = "ROOT_TABLE";
    String priceKey = "lamatola-price";
    String categoryKey = "lamatola-category";

    @PostConstruct
    public void populateCache() throws InterruptedException {
        RedisApi redisApi = new RedisApi(redisRingTemplate);
        redisApi.populateCache(10, rootTable, Optional.empty());
        redisApi.populateCache(10, rootTableExp, Optional.of(10));

        updateExpirableKey(redisApi);

        Set<String> tempKeys = redisApi.getAllKeys();
        System.out.println(rootTableExp + " cache size: " + tempKeys.size());

        redisApi.getAllHashKeys(rootTable);
        redisApi.getRandomHashKeys(rootTable, 1000);

        List<String> hashKeys = Arrays.asList(
                GLOBAL_KEY + "1", GLOBAL_KEY + "2", "DOES_NOT_EXIST_HASH", GLOBAL_KEY + "3"
        );

        lookupMultiHashes(redisApi, hashKeys, rootTable);
        redisApi.getAllHashKeys(rootTable);
        redisApi.getRandomHashKeys(rootTable, 1000);
    }

    private static void lookupMultiHashes(RedisApi redisApi, List<String> hashKeys, String key) {
        List<Object> multiCaches = redisApi.getCache(key, Collections.unmodifiableList(hashKeys));
        Map<String, Object> keys = new HashMap<>();

        for (int i = 0; i < hashKeys.size(); i++) {
            keys.put(hashKeys.get(i), multiCaches.get(i));
        }

        keys.entrySet().stream().forEach(___ -> {
            System.out.println(___.getKey() + ": " + ___.getValue());
            if (___.getValue() == null) {
                System.out.println("    fire second request");
            }
        });
    }

    public void updateExpirableKey(RedisApi redisApi) throws InterruptedException {

        System.out.println("===================");
        for (int i = 111; i < 211; i++) {
            String hashKey = "key-" + i;
            redisApi.addCache(rootTableExp, hashKey, "value-" + i);
        }

        Thread.sleep(10000);

        for (int i = 0; i < 100; i++) {
            String hashKey = "key-" + i;
            System.out.println(rootTableExp + ": " + redisApi.getCache(rootTableExp, hashKey));
        }
    }
}
