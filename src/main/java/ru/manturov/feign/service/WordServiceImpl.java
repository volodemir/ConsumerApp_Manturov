package ru.manturov.feign.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.manturov.dto.WordDto;
import ru.manturov.entity.Word;
import ru.manturov.repository.WordRepository;

import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
@Profile("feign")
public class WordServiceImpl implements WordService {
    private final ObjectMapper objectMapper;
    private final WordRepository wordRepository;

    @Override
    public void consume(WordDto dto) {
        log.info("=> consumed {}", writeValueAsString(dto));
    }

    @Override
    public Word save(WordDto dto) {
        Word word = new Word();
        word.setWord(dto.getWord());
        word.setCreatedDate(new Date());
        wordRepository.save(word);
        return word;
    }

    private String writeValueAsString(WordDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}