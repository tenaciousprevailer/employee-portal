package com.socgen.employeeportal.config;

/**
 * Cache Configuration
 * <p>
 * Current Cache Implementation is backed by Redis using Redisson Client
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.socgen.employeeportal.util.EmployeePortalConstants.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Value("${redis.address}")
    private String redisAddress;

    @Value("${cache.ttl}")
    private String cacheTTL;

    @Value("${cache.max.idle.time}")
    private String cacheMaxIdleTime;

    @Bean
    public Config getRedisConfig() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(redisAddress);
        return config;
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient getRedisClient(Config redisConfig) {
        return Redisson.create(redisConfig);
    }

//    @Bean
//    public javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration(RedissonClient redissonClient) {
//        MutableConfiguration<Object, Object> jcacheConfig = new MutableConfiguration<>();
//        jcacheConfig.setStatisticsEnabled(true);
//        jcacheConfig.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, CACHE_TTL_IN_SECONDS)));
//        return RedissonConfiguration.fromInstance(redissonClient, jcacheConfig);
//    }
//
//    @Bean
//    public JCacheManagerCustomizer cacheManagerCustomizer(javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration) {
//        return cm -> {
//            createCache(cm, EMPLOYEE_BY_ID_CACHE, jcacheConfiguration);
//        };
//    }
//
//    private void createCache(
//            javax.cache.CacheManager cm,
//            String cacheName,
//            javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration) {
//        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
//        if (cache == null) {
//            cm.createCache(cacheName, jcacheConfiguration);
//        }
//    }

    @Bean
    public org.springframework.cache.CacheManager cacheManager(
            RedissonClient redissonClient,
            Map<String, CacheConfig> cacheConfigMap) {

        return new RedissonSpringCacheManager(redissonClient, cacheConfigMap);
    }

    @Bean
    public Map<String, CacheConfig> getCacheConfigMap() {
        Map<String, CacheConfig> cacheConfigMap = new HashMap<>();
        cacheConfigMap.put(EMPLOYEE_BY_ID_CACHE_NAME, new CacheConfig(
                Long.valueOf(cacheTTL), Long.valueOf(cacheMaxIdleTime)));
        return cacheConfigMap;
    }

}
