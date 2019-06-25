package org.camelion.vocabulary.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camelion.vocabulary.exception.VocabularyNotFoundException;
import org.camelion.vocabulary.dto.VocabularyDto;
import org.camelion.vocabulary.mapper.VocabularyMapper;
import org.camelion.vocabulary.model.VocabularyEntity;
import org.camelion.vocabulary.repository.VocabularyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * VocabularyService
 *
 * @author Taha MAAMRI
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VocabularyService {

    private final VocabularyRepository vocabularyRepository;

    Logger logger = LoggerFactory.getLogger(VocabularyService.class);

    /**
     * Get all vocabularies
     *
     * @return list of data
     */
    @Transactional(readOnly = true)
    public List<VocabularyDto> getAll() {
        logger.debug("execute method getAll");
        return vocabularyRepository.findAll().stream()
                .map(VocabularyMapper.INSTANCE::vocabularyEntityToVocabularyDto)
                .collect(Collectors.toList());
    }

    /**
     * Find a single vocabulary by code
     *
     * @param code the code of the requested vocabulary
     * @return the requested {@link org.camelion.vocabulary.dto.VocabularyDto}
     * @throws VocabularyNotFoundException Exception occurred when no vocabulary found in database
     */
    public VocabularyDto findByCode(String code) throws VocabularyNotFoundException {
        logger.debug("execute method findByCode with code : {}", code);
        VocabularyEntity vocabularyEntity = vocabularyRepository.findByCode(code)
                .orElseThrow(() -> new VocabularyNotFoundException(code));
        return VocabularyMapper.INSTANCE.vocabularyEntityToVocabularyDto(vocabularyEntity);
    }

    /**
     * Find a single vocabulary by code
     *
     * @param id the id of the requested vocabulary
     * @return the requested {@link org.camelion.vocabulary.dto.VocabularyDto}
     * @throws VocabularyNotFoundException Exception occurred when no vocabulary found in database
     */
    public VocabularyDto findById(Integer id) throws VocabularyNotFoundException {
        logger.debug("execute method findById with id : {}", id);
        VocabularyEntity vocabularyEntity = vocabularyRepository.findById(id)
                .orElseThrow(() -> new VocabularyNotFoundException(id));
        if (logger.isDebugEnabled()) {
            logger.debug("end method findById");
        }
        return VocabularyMapper.INSTANCE.vocabularyEntityToVocabularyDto(vocabularyEntity);
    }

    /**
     * Create a vocabulary
     *
     * @param vocabularyDto
     * @return the created vocabulary
     */
    public VocabularyDto create(VocabularyDto vocabularyDto) {
        logger.debug("execute method create with entry : {}", vocabularyDto.toString());
        VocabularyEntity vocabularyEntity = VocabularyMapper.INSTANCE.vocabularyDtoToVocabularyEntity(vocabularyDto);
        return VocabularyMapper.INSTANCE.vocabularyEntityToVocabularyDto(vocabularyRepository.save(vocabularyEntity));
    }

    /**
     * Update vocabulary
     *
     * @param id            the id of the requested vocabulary to update
     * @param vocabularyDto vocabulary data to update
     * @return the updated vocabulary
     * @throws VocabularyNotFoundException Exception occurred when no vocabulary found in database
     */
    public VocabularyDto update(Integer id, VocabularyDto vocabularyDto) throws VocabularyNotFoundException {
        logger.debug("execute method update with id : {} and vocabularyDto : {}", id, vocabularyDto.toString());
        vocabularyRepository.findById(id).orElseThrow(() -> new VocabularyNotFoundException(id));
        VocabularyEntity vocabularyEntity = VocabularyMapper.INSTANCE.vocabularyDtoToVocabularyEntity(vocabularyDto);
        vocabularyEntity.setId(id);
        return VocabularyMapper.INSTANCE.vocabularyEntityToVocabularyDto(vocabularyRepository.save(vocabularyEntity));
    }

    /**
     * Find a vocabulary by id and delete it
     *
     * @param id the id of the requested vocabulary to be deleted
     * @throws VocabularyNotFoundException Exception occurred when no vocabulary found in database
     */
    public void delete(Integer id) throws VocabularyNotFoundException {
        logger.debug("execute method delete with id : {}", id);
        VocabularyEntity vocabularyEntity = vocabularyRepository.findById(id)
                .orElseThrow(() -> new VocabularyNotFoundException(id));
        vocabularyRepository.delete(vocabularyEntity);
    }

    /**
     * Find a vocabulary by code and delete it
     *
     * @param code the code of the requested vocabulary to be deleted
     * @throws VocabularyNotFoundException Exception occurred when no vocabulary found in database
     */
    public void delete(String code) throws VocabularyNotFoundException {
        logger.debug("execute method delete with code : {}", code);
        VocabularyEntity vocabularyEntity = vocabularyRepository.findByCode(code)
                .orElseThrow(() -> new VocabularyNotFoundException(code));
        vocabularyRepository.delete(vocabularyEntity);
    }

    /**
     * Delete all vocabularies
     */
    public void deleteAll() {
        logger.debug("execute method deleteAll");
        vocabularyRepository.deleteAll();
    }
}
