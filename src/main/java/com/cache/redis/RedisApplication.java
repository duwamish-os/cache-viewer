package com.cache.redis;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RedisApplication {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public static void main(String... args) throws Exception {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Bean
    public void populateCache() throws InterruptedException {
        String rootTable = "ROOT_TABLE";
        String rootTableTemp = "ROOT_TABLE_TEMP";

        System.out.println("===================");
        for (int i = 0; i < 100; i++) {
            String hashKey = "key-" + i;
            redisTemplate.opsForHash().put(rootTable, hashKey, "value-" + i);
            System.out.println(rootTable + ": " + redisTemplate.opsForHash().get(rootTable, hashKey));
        }

        System.out.println("===================");
        for (int i = 0; i < 100; i++) {
            redisTemplate.opsForHash().put(rootTableTemp, "key-" + i, "value-" + i);
        }

        redisTemplate.expire(rootTableTemp, 10, TimeUnit.MILLISECONDS);

        Thread.sleep(1000);

        for (int i = 0; i < 100; i++) {
            String hashKey = "key-" + i;
            System.out.println(rootTableTemp + ": " + redisTemplate.opsForHash().get(rootTableTemp, hashKey));
        }

        Set<String> keys = redisTemplate.keys(rootTable + "*");
        System.out.println(rootTable + " cache size: " + keys.size());

        Set<String> tempKeys = redisTemplate.keys(rootTableTemp + "*");
        System.out.println(rootTableTemp + " cache size: " + tempKeys.size());
    }

}
