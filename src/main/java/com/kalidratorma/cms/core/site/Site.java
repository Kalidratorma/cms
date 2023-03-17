package com.kalidratorma.cms.core.site;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "site",
        indexes = {@Index(name = "IDX_SITE_NAME", columnList = "name"),
                @Index(name = "IDX_SITE_BASE_URL", columnList = "baseUrl")})
@JsonFilter("SiteFilter")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    @Column(unique = true, nullable = false)
    private String baseUrl;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "site")
    @JsonManagedReference
    private List<Page> pageList;

    @Override
    public String toString() {
        return "Site{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", pageList=" + pageList.stream().map(Page::getPathName).collect(Collectors.joining(",")) +
                '}';
    }
}
