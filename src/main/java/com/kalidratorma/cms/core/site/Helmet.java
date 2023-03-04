package com.kalidratorma.cms.core.site;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "helmet",
        indexes = {@Index(name = "IDX_HELMET_TITLE", columnList = "title"),
                @Index(name = "IDX_HELMET_URL", columnList = "url")
//                ,
//                @Index(name = "IDX_HELMET_PAGE_ID_TITLE", columnList = "page_id, title")
})
public class Helmet {
    @TableGenerator(
            name = "helmetGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "helmet_id",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "helmetGen")
    @Column(name = "id", updatable = false)
    private Long id;
    private String title;
    private String description;
    private String url;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Page page;

    public Helmet() {
    }

    public Helmet(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Helmet{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url=" + url +
                '}';
    }
}
