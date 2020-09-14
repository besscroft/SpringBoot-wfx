package com.bess.springboot.wfx;

import com.bess.springboot.wfx.config.ESConfig;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.List;

@SpringBootApplication
@MapperScan(basePackages = "com.bess.springboot.wfx.dao")
@ServletComponentScan(basePackages = "com.bess.springboot.wfx.filter")
public class SpringbootWfxApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors","false");
        SpringApplication.run(SpringbootWfxApplication.class, args);
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    @Bean
    public DefaultRedisScript<List> defaultRedisScript(){
        DefaultRedisScript<List> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(List.class);
        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("unlock.lua")));
        return  defaultRedisScript;
    }
}
