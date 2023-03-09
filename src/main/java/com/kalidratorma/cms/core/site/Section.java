package com.kalidratorma.cms.core.site;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnTransformer;

import java.util.Map;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "section")
public class Section {
    @TableGenerator(
            name = "sectionGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "section_id",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sectionGen")
    @Column(name = "id", updatable = false)
    private Long id;
    private String name;
    private String title;
    private String description;

    @Column(columnDefinition = "jsonb")
    @Convert(converter = ContentHashMapConverter.class)
    @ColumnTransformer(write = "?::jsonb")
    private Map<String, Object> content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Page page;

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content.hashCode() + '\'' +
                '}';
    }
}
