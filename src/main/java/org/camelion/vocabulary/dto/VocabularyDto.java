package org.camelion.vocabulary.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * VocabularyDto
 *
 * @author Taha MAAMRI
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyDto {
    private Integer id;
    @NotNull
    private String code;
    private String realCode;
    private String label;
    private byte[] image;
    private String description;
    private Instant createdDate;
    private String createdBy;
    private Instant lastModifiedDate;
    private String lastModifiedby;
}
