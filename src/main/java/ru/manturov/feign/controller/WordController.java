package ru.manturov.feign.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import ru.manturov.dto.WordDto;
import ru.manturov.feign.client.ApiClient;
import ru.manturov.feign.service.WordServiceImpl;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("word")
@Slf4j
@Profile("feign")
public class WordController {
    ApiClient client;
    WordServiceImpl wordService;

    @GetMapping
    @Scheduled(fixedDelayString = "${feign.consumer.consume.interval}")
    public ResponseEntity<WordDto> getWord() {
        WordDto wordDto = client.getWord();
        wordService.consume(wordDto);
        wordService.save(wordDto);
        return ResponseEntity.of(Optional.of(wordDto));
    }
}