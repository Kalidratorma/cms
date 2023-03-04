package com.kalidratorma.cms.core.site;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class SiteController {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private Environment environment;

    @PostMapping("/site")
    public ResponseEntity<Site> createSite(
            @RequestBody Site site) {
        Site savedSite = siteRepository.save(site);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{siteName}")
                .buildAndExpand(savedSite.getName())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/site/{siteName}")
    public Site readSite(
            @PathVariable String siteName) {
        Site site = siteRepository.findByName(siteName);
        if (site == null) {
            throw new RuntimeException("Unable to Find Site with name " + siteName);
        }
        //String port = environment.getProperty("local.server.port");
        return site;
    }

    @PutMapping("/site")
    public ResponseEntity<Site> updateSite(
            @RequestBody Site site) {
        Site origSite = siteRepository.findByName(site.getName());

        Site savedSite = siteRepository.save(site);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{siteName}")
                .buildAndExpand(savedSite.getName())
                .toUri();
        return ResponseEntity.created(location).build();
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

        //String port = environment.getProperty("local.server.port");
        return page;
    }
}
