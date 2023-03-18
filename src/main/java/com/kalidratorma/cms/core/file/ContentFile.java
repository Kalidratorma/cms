package com.kalidratorma.cms.core.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kalidratorma.cms.core.site.Site;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "content_file",
        indexes = {@Index(name = "IDX_CONTENT_FILE_NAME", columnList = "site_id, name", unique = true) } )
class ContentFile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String name;

    @JsonIgnore
    String location;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Site site;

    public ContentFile (Site site, String name, String location) {
        this.site = site;
        this.name = name;
        this.location = location;
    }
}
