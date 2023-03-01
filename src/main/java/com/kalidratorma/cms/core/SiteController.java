package com.kalidratorma.cms.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteController {

    private Logger logger = LoggerFactory.getLogger(SiteRepository.class);

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private Environment environment;

    @GetMapping("/site/{name}")
    public Site retrieveSite (
            @PathVariable String name) {

        logger.info("retrieveSite called with {}", name);

        Site site = siteRepository.findByName(name);

//        if (site == null) {
//            throw new RuntimeException("Unable to Find Site with name " + name);
//        }

        //String port = environment.getProperty("local.server.port");
        return site;
    }

    @GetMapping("/site/{siteName}/{pathName}")
    public Page retrievePage (
            @PathVariable String siteName,
            @PathVariable String pathName
            ) {

        logger.info("retrievePage from Site {} with pathName {}", siteName, pathName);

        Site site = siteRepository.findByName(siteName);

        Page page = site.getPageList().stream()
                .filter(page1 -> pathName.equals(page1.getPathName()))
                .findAny().orElse(null);

//        if (site == null) {
//            throw new RuntimeException("Unable to Find Site with name " + name);
//        }

        //String port = environment.getProperty("local.server.port");
        return page;
    }
}
