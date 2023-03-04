package com.kalidratorma.cms.core.site;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "navigation")
public class Navigation {
    @TableGenerator(
            name = "navigationGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "navigation_id",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "navigationGen")
    @Column(name = "id", updatable = false)
    private Long id;
    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "navigation")
    @JsonManagedReference
    private List<NavLink> links;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Page page;

    public Navigation() {
    }
    public Navigation(List<NavLink> links) {
        this.links = links;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
