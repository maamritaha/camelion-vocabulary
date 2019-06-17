package org.camelion.vocabulary.model;


import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;

/**
 * VocabularyEntity represent vocabularies will be used in the user interfaces lists
 *
 * @author Taha MAAMRI
 * @since 1.0
 */
@Entity
@Table(name = "REF_VOCABULARY")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"createdDate", "createdBy", "lastModifiedDate", "LastModifiedBy"})
@ToString
public class VocabularyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "VOC_CODE", nullable = false, unique = true)
    private String code;
    @Column(name = "VOC_REAL_CODE")
    private String realCode;
    @Column(name = "VOC_LABEL", nullable = false)
    private String label;
    @Lob
    @Column(name = "VOC_IMAGE")
    private byte[] image;
    @Column(name = "VOC_DESCRIPTION")
    private String description;
    @CreatedDate
    private Instant createdDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private Instant lastModifiedDate;
    @LastModifiedBy
    private String LastModifiedBy;
}
