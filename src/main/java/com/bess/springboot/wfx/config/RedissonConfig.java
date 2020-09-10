package com.bess.springboot.wfx.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Bess Croft
 * @DateTime 2020/9/10 15:07
 */
@Configuration
public class RedissonConfig {

    @Value("${redisson.addr.singleAddr.host}")
    private String host;
    @Value("${redisson.addr.singleAddr.password}")
    private String password;
    @Value("${redisson.addr.singleAddr.database}")
    private int database;
    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress(host).setPassword(password).setDatabase(database);
        return Redisson.create(config);
    }

}
