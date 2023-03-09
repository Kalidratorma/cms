package com.kalidratorma.cms.core.site;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
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
}
