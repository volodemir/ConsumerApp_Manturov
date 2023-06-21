package ru.manturov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.manturov.entity.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
}