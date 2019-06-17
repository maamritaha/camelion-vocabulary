package org.camelion.vocabulary.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camelion.vocabulary.Exception.VocabularyNotFoundException;
import org.camelion.vocabulary.dto.VocabularyDto;
import org.camelion.vocabulary.mapper.VocabularyMapper;
import org.camelion.vocabulary.model.VocabularyEntity;
import org.camelion.vocabulary.repository.VocabularyRepository;
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

    /**
     * Get all vocabularies
     *
     * @return list of data
     */
    @Transactional(readOnly = true)
    public List<VocabularyDto> getAll() {
        return vocabularyRepository.findAll().stream()
                .map(VocabularyMapper.INSTANCE::vocabularyEntityToVocabularyDto)
                .collect(Collectors.toList());
    }

    /**
     * Find a single vocabulary by code
     *
     * @param code the code of the requested vocabulary
     * @return the requested {@link VocabularyDto}
     * @throws VocabularyNotFoundException
     */
    public VocabularyDto findByCode(String code) throws VocabularyNotFoundException {
        VocabularyEntity vocabularyEntity = vocabularyRepository.findByCode(code)
                .orElseThrow(() -> new VocabularyNotFoundException());
        return VocabularyMapper.INSTANCE.vocabularyEntityToVocabularyDto(vocabularyEntity);
    }

    /**
     * Find a single vocabulary by code
     *
     * @param id the id of the requested vocabulary
     * @return the requested {@link VocabularyDto}
     * @throws VocabularyNotFoundException
     */
    public VocabularyDto findById(Integer id) throws VocabularyNotFoundException {
        VocabularyEntity vocabularyEntity = vocabularyRepository.findById(id)
                .orElseThrow(() -> new VocabularyNotFoundException(id));
        return VocabularyMapper.INSTANCE.vocabularyEntityToVocabularyDto(vocabularyEntity);
    }

    /**
     * Create a vocabulary
     *
     * @param vocabularyDto
     * @return the created vocabulary
     */
    public VocabularyDto create(VocabularyDto vocabularyDto) {
        VocabularyEntity vocabularyEntity = VocabularyMapper.INSTANCE.vocabularyDtoToVocabularyEntity(vocabularyDto);
        return VocabularyMapper.INSTANCE.vocabularyEntityToVocabularyDto(vocabularyRepository.save(vocabularyEntity));
    }

    /**
     * Update vocabulary
     *
     * @param id the id of the requested vocabulary to update
     * @param vocabularyDto vocabulary data to update
     * @return the updated vocabulary
     * @throws VocabularyNotFoundException
     */
    public VocabularyDto update(Integer id, VocabularyDto vocabularyDto) throws VocabularyNotFoundException {
        VocabularyEntity vocabularyEntity = vocabularyRepository.findById(id).map(record -> {
            record.setCode(vocabularyDto.getCode());
            record.setRealCode(vocabularyDto.getRealCode());
            record.setLabel(vocabularyDto.getLabel());
            record.setImage(vocabularyDto.getImage());
            record.setDescription(vocabularyDto.getDescription());
            return record;
        }).orElseThrow(() -> new VocabularyNotFoundException(id));
        return VocabularyMapper.INSTANCE.vocabularyEntityToVocabularyDto(vocabularyRepository.save(vocabularyEntity));
    }

    /**
     * Find a vocabulary by id and delete it
     *
     * @param id the id of the requested vocabulary to be deleted
     * @throws VocabularyNotFoundException
     */
    public void delete(Integer id) throws VocabularyNotFoundException{
        VocabularyEntity vocabularyEntity = vocabularyRepository.findById(id)
                .orElseThrow(() -> new VocabularyNotFoundException(id));
        vocabularyRepository.delete(vocabularyEntity);
    }

    /**
     * Find a vocabulary by code and delete it
     *
     * @param code the code of the requested vocabulary to be deleted
     * @throws VocabularyNotFoundException
     */
    public void delete(String code) throws VocabularyNotFoundException{
        VocabularyEntity vocabularyEntity = vocabularyRepository.findByCode(code)
                .orElseThrow(() -> new VocabularyNotFoundException(code));
        vocabularyRepository.delete(vocabularyEntity);
    }

    /**
     * Delete all vocabularies
     */
    public void deleteAll(){
        vocabularyRepository.deleteAll();
    }
}
