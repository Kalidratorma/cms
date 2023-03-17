package com.kalidratorma.cms.core.file;

import com.kalidratorma.cms.core.site.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ContentFileRepository extends JpaRepository<ContentFile, Long> {

    Optional<ContentFile> findTopBySiteAndNameOrderByIdDesc(Site site, String name);
}