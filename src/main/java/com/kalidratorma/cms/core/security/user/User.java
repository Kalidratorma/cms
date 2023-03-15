package com.kalidratorma.cms.core.security.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kalidratorma.cms.core.site.Site;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sec_user")
public class User implements UserDetails {
    @TableGenerator(
            name = "userGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "user_id",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "userGen")
    private int id;

    @Column(unique = true)
    private String username;
    private String firstname;
    private String lastname;

    @Column(unique = true)
    private String email;

    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Site> sites;

    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
