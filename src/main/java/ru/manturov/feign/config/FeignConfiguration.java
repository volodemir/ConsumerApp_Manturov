package ru.manturov.feign.config;

import feign.Contract;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.*;
import ru.manturov.feign.client.ApiClient;

@Configuration
@PropertySource("classpath:feign.properties")
@Import(FeignClientsConfiguration.class)
@EnableFeignClients
@Profile("feign")
public class FeignConfiguration {

    @Bean
    public ApiClient getClient(Contract c) {
        return Feign.builder().
                contract(c).
                encoder(new JacksonEncoder()).
                decoder(new JacksonDecoder()).target(ApiClient.class,
                        "http://localhost:8081");
    }
}