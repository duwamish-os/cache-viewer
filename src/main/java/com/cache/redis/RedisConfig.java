package com.cache.redis;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.resource.ClientResources;
import io.micrometer.core.lang.NonNull;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${redis.timeout}")
    private long timeout = 5000;

    @Value("${redis.sentinel.master}")
    String redisSentinelMaster;

    @Value("${redis.sentinel.nodes}")
    List<String> redisSentinelNodes;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redis = new RedisTemplate<>();
        redis.setConnectionFactory(connectionFactory);
        redis.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        redis.setKeySerializer(new StringRedisSerializer());
        redis.setHashKeySerializer(new StringRedisSerializer());
        redis.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redis.afterPropertiesSet();
        return redis;
    }

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        List<RedisNode> redisNodes = getRedisNodes(redisSentinelNodes);
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        redisSentinelConfiguration.master(redisSentinelMaster);
        redisSentinelConfiguration.setSentinels(redisNodes);

        return new LettuceConnectionFactory(redisSentinelConfiguration, getLettuceClientConfiguration());
    }

    List<RedisNode> getRedisNodes(List<String> redisSentinelNodes) {
        List<RedisNode> redisNodes = new ArrayList<>();
        for (String node : redisSentinelNodes) {
            String[] parts = node.split(":");
            if (parts.length < 2) {
                throw new IllegalStateException("redis.sentinel.nodes must have port number");
            }
            redisNodes.add(new RedisNode(parts[0].trim(), Integer.parseInt(parts[1])));
        }

        if (redisNodes.size() < 1) {
            throw new IllegalStateException("Must be at least 2 redis.sentinel.nodes");
        }

        return redisNodes;
    }

    LettuceClientConfiguration getLettuceClientConfiguration() {
        return new LettuceClientConfiguration() {
            @Override
            public boolean isUseSsl() {
                return false;
            }

            @Override
            public boolean isVerifyPeer() {
                return false;
            }

            @Override
            public boolean isStartTls() {
                return false;
            }

            @Override
            @NonNull
            public Optional<ClientResources> getClientResources() {
                return Optional.empty();
            }

            @Override
            @NonNull
            public Optional<ClientOptions> getClientOptions() {
                return Optional.empty();
            }

            @Override
            @NonNull
            public Optional<String> getClientName() {
                return Optional.empty();
            }

            @Override
            @NonNull
            public Optional<ReadFrom> getReadFrom() {
                return Optional.empty();
            }

            @Override
            @NonNull
            public Duration getCommandTimeout() {
                return Duration.ofMillis(timeout);
            }

            @Override
            @NonNull
            public Duration getShutdownTimeout() {
                return Duration.ofMillis(timeout);
            }

            @Override
            @NonNull
            public Duration getShutdownQuietPeriod() {
                return Duration.ofMillis(timeout);
            }
        };
    }


}
