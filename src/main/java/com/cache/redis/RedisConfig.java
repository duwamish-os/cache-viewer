package com.cache.redis;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.resource.ClientResources;
import io.micrometer.core.lang.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class RedisConfig {

    @Value("${redis.timeout:5000}")
    private long timeout;

    @Value("${redis.sentinel.master:redis_ring}")
    String redisSentinelMaster;

    @Value("${redis.sentinel.nodes:172.25.0.13:26379,172.25.0.14:26379}")
    List<String> redisSentinelNodes;

    /**
     * see docker-compose.yml for container to container network on bridge private network
     */
    static String container_host = "172.25.0.11";

    @Bean("standaloneRedisTemplate")
    RedisTemplate<String, Object> getStandaloneRedisTemplate(
            @Qualifier("standaloneConnectionFactory") RedisConnectionFactory standaloneConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(standaloneConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean("standaloneRedisConfiguration")
    RedisStandaloneConfiguration getStandaloneRedisConfiguration() {
        return new RedisStandaloneConfiguration(container_host, 6379);
    }

    @Bean("standaloneConnectionFactory")
    public RedisConnectionFactory standaloneConnectionFactory(RedisStandaloneConfiguration configuration) {
        RedisConnectionFactory connectionFactory = new LettuceConnectionFactory(configuration);
        return connectionFactory;
    }

    //Redis ring
    @Bean("sentinelRedisConfiguration")
    RedisSentinelConfiguration getSentinelRedisConfiguration() {
        List<RedisNode> redisNodes = getRedisNodes(redisSentinelNodes);
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        redisSentinelConfiguration.master(redisSentinelMaster);
        redisSentinelConfiguration.setSentinels(redisNodes);

        return redisSentinelConfiguration;
    }

    @Bean("redisRingTemplate")
    public RedisTemplate<String, Object> redisRingTemplate(
            @Qualifier("ringConnectionFactory") LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redis = new RedisTemplate<>();
        redis.setConnectionFactory(connectionFactory);

        redis.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        redis.setKeySerializer(new StringRedisSerializer());

        redis.setHashKeySerializer(new StringRedisSerializer());
        redis.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        redis.afterPropertiesSet();

        return redis;
    }

    @Bean("ringConnectionFactory")
    @Primary
    public LettuceConnectionFactory ringConnectionFactory(
            @Qualifier("sentinelRedisConfiguration") RedisSentinelConfiguration redisSentinelConfiguration) {
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
