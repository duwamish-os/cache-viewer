package com.cache.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@SpringBootApplication
public class RedisMasterConnection {

    static String key = "ml_candidate_id";
    static String host_localhost = "localhost";
    static String host_127_0_0_1 = "127.0.0.1";
    static int port = 6379;
    static String redis = String.format("redis://%s:%d", host_localhost, port);

    @Autowired
    RedisTemplate<String, Object> standaloneRedisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RedisMasterConnection.class, args);
    }

    /**
     * 127.0.0.1:6379> get ml_candidate_id
     * "2023-04-28T20:51:07.444259"
     */
    private void jedisRedisExample(String key, String value) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set(key, value);
        String value1 = jedis.get(key);
        System.out.println("The value of the key is: " + value1);
        jedis.close();
    }

    private void lettuceRedisExample(String key, String value) {
        ValueOperations<String, Object> operations = standaloneRedisTemplate.opsForValue();
        operations.set(key, value);
        String value1 = (String) operations.get(key);
        System.out.println("The value of the key is: " + value1);
    }

    public static void lettuce() {
        RedisURI redisURI = new RedisURI("localhost", 6379, Duration.ofMillis(500));

        RedisClient redisClient = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connection = redisClient.connect();

        RedisCommands<String, String> commands = connection.sync();
        commands.set(key, "lettuce__" + ZonedDateTime.now());
        String value = commands.get(key);

        System.out.println("The value of the key is: " + value);
        connection.close();
    }
    @PostConstruct
    public void after() {
        //        jedisRedisExample(key, "jedis_" + LocalDateTime.now().toString());
        lettuce();
        lettuceRedisExample(key, "lettuce_spring_" + LocalDateTime.now().toString());
    }
}
