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
                @Index(name = "IDX_HELMET_URL", columnList = "url")
//                ,
//                @Index(name = "IDX_HELMET_PAGE_ID_TITLE", columnList = "page_id, title")
})
public class Helmet {
    @TableGenerator(
            name = "helmetGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "helmet_id",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "helmetGen")
    @Column(name = "id", updatable = false)
    private Long id;
    private String title;
    private String description;
    private String url;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Page page;
}
