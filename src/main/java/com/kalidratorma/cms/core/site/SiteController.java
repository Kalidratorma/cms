package com.kalidratorma.cms.core.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SiteController {

    @Autowired
    private SiteRepository siteRepository;

    @PostMapping("/site")
    public ResponseEntity<String> createSite(
            @RequestBody Site site) {
        siteRepository.save(site);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/site/{siteName}")
    public Site readSite(
            @PathVariable String siteName) {
        Site site = siteRepository.findByName(siteName);
        if (site == null) {
            throw new RuntimeException("Unable to Find Site with name " + siteName);
        }
        return site;
    }

    @PutMapping("/site")
    public ResponseEntity<String> updateSite(
            @RequestBody Site site) {
        Site origSite = siteRepository.findByName(site.getName());
        if (origSite == null) {
            throw new RuntimeException("Unable to Find Site with name " + site.getName());
        }
        site.setId(origSite.getId());
        siteRepository.save(site);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/site/{siteName}")
    public ResponseEntity<String> deleteSite(
            @PathVariable String siteName) {
        Site origSite = siteRepository.findByName(siteName);
        if (origSite == null) {
            throw new RuntimeException("Unable to Find Site with name " + siteName);
        }
        siteRepository.deleteById(origSite.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/site/{siteName}/{pathName}")
    public Page retrievePage (
            @PathVariable String siteName,
            @PathVariable String pathName
            ) {
        Site site = siteRepository.findByName(siteName);
        if (site == null) {
            throw new RuntimeException("Unable to Find Site with name " + siteName);
        }
        Page page = site.getPageList().stream()
                .filter(page1 -> pathName.equals(page1.getPathName()))
                .findAny().orElse(null);
        if (page == null) {
            throw new RuntimeException("Unable to Find Page ["+pathName+"] for Site [" + siteName +"]");
        }
        return page;
    }
}
