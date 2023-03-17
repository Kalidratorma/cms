package com.kalidratorma.cms.core.file;

import com.kalidratorma.cms.core.site.Site;
import com.kalidratorma.cms.core.site.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
@RequiredArgsConstructor
class FileLocationService {

    private final FileSystemRepository fileSystemRepository;

    private final ContentFileRepository contentFileRepository;

    private final SiteRepository siteRepository;

    String save(String siteName, byte[] bytes, String imageName) throws Exception {
        Site site = siteRepository.findByName(siteName).orElseThrow();
        String date = Long.toString(new Date().getTime()/1000);
        String imageNameWithDate = date + "-" + imageName;
        String location = fileSystemRepository.save(siteName, bytes, imageNameWithDate);
        contentFileRepository.save(new ContentFile(site, imageNameWithDate, location));

        return "/site/" + siteName + "/files/" + imageNameWithDate;
    }

    FileSystemResource find(String siteName, String fileName) {
        Site site = siteRepository.findByName(siteName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ContentFile contentFile = contentFileRepository
                .findTopBySiteAndNameOrderByIdDesc(site, fileName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.findInFileSystem(contentFile.getLocation());
    }

}