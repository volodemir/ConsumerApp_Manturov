package ru.manturov.feign.service;

import ru.manturov.dto.WordDto;
import ru.manturov.entity.Word;

public interface WordService {
    Word save (WordDto dto);
    void consume(WordDto dto);
}