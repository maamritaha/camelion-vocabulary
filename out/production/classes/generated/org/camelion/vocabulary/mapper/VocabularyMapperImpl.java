package org.camelion.vocabulary.mapper;

import java.util.Arrays;
import javax.annotation.Generated;
import org.camelion.vocabulary.dto.VocabularyDto;
import org.camelion.vocabulary.model.VocabularyEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-06-21T03:11:31+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.3 (Oracle Corporation)"
)
@Component
public class VocabularyMapperImpl implements VocabularyMapper {

    @Override
    public VocabularyDto vocabularyEntityToVocabularyDto(VocabularyEntity vocabularyEntity) {
        if ( vocabularyEntity == null ) {
            return null;
        }

        VocabularyDto vocabularyDto = new VocabularyDto();

        vocabularyDto.setId( vocabularyEntity.getId() );
        vocabularyDto.setCode( vocabularyEntity.getCode() );
        vocabularyDto.setRealCode( vocabularyEntity.getRealCode() );
        vocabularyDto.setLabel( vocabularyEntity.getLabel() );
        byte[] image = vocabularyEntity.getImage();
        if ( image != null ) {
            vocabularyDto.setImage( Arrays.copyOf( image, image.length ) );
        }
        vocabularyDto.setDescription( vocabularyEntity.getDescription() );
        vocabularyDto.setCreatedDate( vocabularyEntity.getCreatedDate() );
        vocabularyDto.setCreatedBy( vocabularyEntity.getCreatedBy() );
        vocabularyDto.setLastModifiedDate( vocabularyEntity.getLastModifiedDate() );

        return vocabularyDto;
    }

    @Override
    public VocabularyEntity vocabularyDtoToVocabularyEntity(VocabularyDto vocabularyDto) {
        if ( vocabularyDto == null ) {
            return null;
        }

        VocabularyEntity vocabularyEntity = new VocabularyEntity();

        vocabularyEntity.setId( vocabularyDto.getId() );
        vocabularyEntity.setCode( vocabularyDto.getCode() );
        vocabularyEntity.setRealCode( vocabularyDto.getRealCode() );
        vocabularyEntity.setLabel( vocabularyDto.getLabel() );
        byte[] image = vocabularyDto.getImage();
        if ( image != null ) {
            vocabularyEntity.setImage( Arrays.copyOf( image, image.length ) );
        }
        vocabularyEntity.setDescription( vocabularyDto.getDescription() );
        vocabularyEntity.setCreatedDate( vocabularyDto.getCreatedDate() );
        vocabularyEntity.setCreatedBy( vocabularyDto.getCreatedBy() );
        vocabularyEntity.setLastModifiedDate( vocabularyDto.getLastModifiedDate() );

        return vocabularyEntity;
    }
}
