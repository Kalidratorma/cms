package com.kalidratorma.cms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Navigation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    @OneToMany(mappedBy = "navigation")
    //@JsonIgnore
    private List<NavLink> links;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Page page;

    public Navigation() {
    }
    public Navigation(List<NavLink> links) {
        this.links = links;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<NavLink> getLinks() {
        return links;
    }

    public void setLinks(List<NavLink> links) {
        this.links = links;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Navigation{" +
                "links=" + links +
                '}';
    }

}
