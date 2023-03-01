package com.kalidratorma.cms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    private String pathName;
    @OneToOne(mappedBy = "page")
  //  @JsonIgnore
    private Helmet helmet;
    @OneToOne(mappedBy = "page")
  //  @JsonIgnore
    private Navigation nav;

    @OneToMany(mappedBy = "page")
//    @JsonIgnore
    private List<Section> sections;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Site site;

    public Page() {
    }

    public Page(String pathName, Helmet helmet, Navigation nav, List<Section> sections) {
        this.pathName = pathName;
        this.helmet = helmet;
        this.nav = nav;
        this.sections = sections;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
