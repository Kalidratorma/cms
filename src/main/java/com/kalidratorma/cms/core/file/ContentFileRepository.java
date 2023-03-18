package com.kalidratorma.cms.core.file;

import com.kalidratorma.cms.core.site.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface ContentFileRepository extends JpaRepository<ContentFile, Long> {

    Optional<ContentFile> findTopBySiteIdAndNameOrderByIdDesc(Long siteId, String name);
    Optional<List<ContentFile>> findAllBySiteId (Long siteId);

    void deleteBySiteIdAndName(Long siteId, String name);
}