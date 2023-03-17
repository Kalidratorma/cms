package com.kalidratorma.cms.core.site;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "helmet",
        indexes = {@Index(name = "IDX_HELMET_TITLE", columnList = "title"),
                @Index(name = "IDX_HELMET_URL", columnList = "url")})
public class Helmet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;
    private String title;
    private String description;
    private String url;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Page page;
}
