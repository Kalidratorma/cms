package com.kalidratorma.cms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class NavLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String label;
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Navigation navigation;

    public NavLink() {
    }

    public NavLink(String label, String link) {
        this.label = label;
        this.link = link;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    @Override
    public String toString() {
        return "NavLink{" +
                "label='" + label + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
