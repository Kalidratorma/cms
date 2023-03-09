package com.kalidratorma.cms.core.site;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "page",
        indexes = {@Index(name = "IDX_PAGE_PATH_NAME", columnList = "path_name")
//                ,
//                @Index(name = "IDX_PAGE_SITE_ID_PATH_NAME", columnList = "site_id, path_name")
})
public class Page {
    @TableGenerator(
            name = "pageGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "page_id",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pageGen")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name="path_name")
    private String pathName;
    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "page")
    @JsonManagedReference
    private Helmet helmet;
    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "page")
    @JsonManagedReference
    private Navigation nav;

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "page")
    @JsonManagedReference
    private List<Section> sections;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Site site;
}
