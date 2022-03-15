package org.camelion.vocabulary.mapper;

import org.camelion.vocabulary.dto.VocabularyDto;
import org.camelion.vocabulary.model.VocabularyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * VocabularyMapper
 *
 * @author Taha MAAMRI
 * @since 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VocabularyMapper {

    VocabularyMapper INSTANCE = Mappers.getMapper(VocabularyMapper.class);

    VocabularyDto vocabularyEntityToVocabularyDto(VocabularyEntity vocabularyEntity);
    VocabularyEntity vocabularyDtoToVocabularyEntity(VocabularyDto vocabularyDto);
}
