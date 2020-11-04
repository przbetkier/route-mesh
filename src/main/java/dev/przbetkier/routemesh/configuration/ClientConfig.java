package dev.przbetkier.routemesh.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
class ClientConfig {

    @Bean(name = "trafficRestTemplate")
    RestTemplate trafficRestTemplate(TrafficClientProperties properties) {
        return new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(properties.getConnectTimeout()))
                .setReadTimeout(Duration.ofMillis(properties.getReadTimeout()))
                .build();
    }
}
