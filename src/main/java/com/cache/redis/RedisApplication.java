package com.cache.redis;

import java.util.Optional;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RedisApplication {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public static void main(String... args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Bean
    RedisApi redisApi(){
        return new RedisApi(redisTemplate);
    }

    String rootTableExp = "ROOT_TABLE_EXPIRABLE";
    String rootTable = "ROOT_TABLE";

    @PostConstruct
    public void populateCache() throws InterruptedException {
        RedisApi redisApi = new RedisApi(redisTemplate);
        redisApi.populateCache(10, rootTable, Optional.empty());
        redisApi.populateCache(10, rootTableExp, Optional.of(10));

        updateExpirableKey(redisApi);

        Set<String> tempKeys = redisApi.getAllKeys();
        System.out.println(rootTableExp + " cache size: " + tempKeys.size());

        redisApi.getAllHashKeys(rootTableExp);
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
