package com.kalidratorma.cms.core.site;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "nav_link")
public class NavLink {
    @TableGenerator(
            name = "navLinkGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "nav_link_id",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "navLinkGen")
    @Column(name = "id", updatable = false)
    private Long id;
    private String label;
    private String link;

    @Column(columnDefinition="boolean default false")
    private boolean isExternal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Navigation navigation;

    public NavLink() {
    }

    public NavLink(String label, String link, boolean isExternal) {
        this.label = label;
        this.link = link;
        this.isExternal = isExternal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isExternal() {
        return isExternal;
    }

    public void setExternal(boolean external) {
        isExternal = external;
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
                ", isExternal='" + isExternal + '\'' +
                '}';
    }
}
