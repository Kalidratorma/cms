package com.kalidratorma.cms.core;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, Long> {
    Site findByName(String name);
}