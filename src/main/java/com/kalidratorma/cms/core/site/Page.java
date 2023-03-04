package com.kalidratorma.cms.core.site;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
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

    public Page() {
    }

    public Page(String pathName, Helmet helmet, Navigation nav, List<Section> sections) {
        this.pathName = pathName;
        this.helmet = helmet;
        this.nav = nav;
        this.sections = sections;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public Helmet getHelmet() {
        return helmet;
    }

    public void setHelmet(Helmet helmet) {
        this.helmet = helmet;
    }

    public Navigation getNav() {
        return nav;
    }

    public void setNav(Navigation nav) {
        this.nav = nav;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pathName='" + pathName + '\'' +
                ", helmet=" + helmet +
                ", nav=" + nav +
                ", sections=" + sections +
                '}';
    }
}
