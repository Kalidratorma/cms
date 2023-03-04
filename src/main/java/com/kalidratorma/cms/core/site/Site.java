package com.kalidratorma.cms.core.site;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "site",
        indexes = {@Index(name = "IDX_SITE_NAME", columnList = "name"),
                @Index(name = "IDX_SITE_BASE_URL", columnList = "baseUrl")})
public class Site {
    @TableGenerator(
            name = "siteGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "site_id",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "siteGen")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    @Column(unique = true, nullable = false)
    private String baseUrl;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "site")
    @JsonManagedReference
    // @JsonIgnore
    private List<Page> pageList;

    public Site() {
    }

    public Site(Long id, String name, String description, String baseUrl, List<Page> pageList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.baseUrl = baseUrl;
        this.pageList = pageList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<Page> getPageList() {
        return pageList;
    }

    public void setPageList(List<Page> pageList) {
        this.pageList = pageList;
    }

    @Override
    public String toString() {
        return "Site{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", pageList=" + pageList +
                '}';
    }
}
