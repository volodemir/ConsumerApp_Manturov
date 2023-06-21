package ru.manturov.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import ru.manturov.dto.WordDto;
import ru.manturov.feign.config.FeignConfiguration;

@FeignClient(value = "WORD-CONSUMER", url = "http://localhost:8081", configuration = FeignConfiguration.class)
@Profile("feign")
public interface ApiClient {

    @GetMapping("word")
    WordDto getWord();
}