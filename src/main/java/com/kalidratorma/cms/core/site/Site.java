package com.kalidratorma.cms.core.site;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String description;
    @Column(unique = true)
    private String baseUrl;

    @OneToMany(mappedBy = "site")
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
