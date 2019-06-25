package org.camelion.vocabulary.model;


import lombok.*;
import org.springframework.context.annotation.Lazy;
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
@Table(name = "ref_vocabulary")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"createdDate", "createdBy", "lastModifiedDate", "LastModifiedBy"})
@ToString
@SequenceGenerator(name = "seq_ref_vocabulary", sequenceName = "seq_ref_vocabulary")
public class VocabularyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ref_vocabulary")
    @Column(name = "voc_id")
    private Integer id;
    @Column(nullable = false, unique = true)
    private String code;
    private String realCode;
    @Column(nullable = false)
    private String label;
    private Boolean active;
    private Boolean visible;
    @Lazy
    @Lob
    private byte[] image;
    private String description;
    @CreatedDate
    private Instant createdDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private Instant lastModifiedDate;
    @LastModifiedBy
    private String LastModifiedBy;

    @PrePersist
    private void setAnonymousUser() {
        //TODO to replace by spring security
        setCreatedBy("anonymousUser");
        setLastModifiedBy("anonymousUser");
        setCreatedDate(Instant.now());
        setLastModifiedDate(Instant.now());
    }
}
