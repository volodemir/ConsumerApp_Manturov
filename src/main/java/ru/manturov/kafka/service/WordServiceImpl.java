package ru.manturov.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.manturov.dto.WordDto;
import ru.manturov.entity.Word;
import ru.manturov.repository.WordRepository;

import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
@Profile("kafka")
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;
    private final ObjectMapper objectMapper;

    @Override
    @KafkaListener(id = "WordSave", topics = {"wordTopic"}, containerFactory = "singleFactory")
    public Word save(WordDto dto) {
        Word word = new Word();
        word.setWord(dto.getWord());
        word.setCreatedDate(new Date());
        wordRepository.save(word);
        return word;
    }

    @Override
    @KafkaListener(id = "Word", topics = {"wordTopic"}, containerFactory = "singleFactory")
    public void consume(WordDto dto) {
        log.info("=> consumed {}", writeValueAsString(dto));
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