package org.camelion.vocabulary.repository;

import org.camelion.vocabulary.model.VocabularyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository of {@link VocabularyEntity}
 */
public interface VocabularyRepository extends JpaRepository<VocabularyEntity, Integer> {
    Optional<VocabularyEntity> findByCode(String code);
}
