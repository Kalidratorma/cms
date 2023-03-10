package com.kalidratorma.cms.core.site;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
public class SiteController {

    private final SiteRepository siteRepository;

    @GetMapping("/site")
    public MappingJacksonValue readSites() {
        List<Site> siteList = siteRepository.findAll();
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("pageList");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SiteFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(siteList);
        mapping.setFilters(filters);
        return mapping;
    }

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

    @GetMapping(value = "/siteAsFile/{siteName}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody ResponseEntity getSiteAsFile(
            @PathVariable String siteName) throws IOException {

        Site site = siteRepository.findByName(siteName);
        if (site == null) {
            throw new RuntimeException("Unable to Find Site with name " + siteName);
        }
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("SiteFilter", SimpleBeanPropertyFilter.serializeAll());
        mapper.setFilterProvider(filterProvider);
        String json = mapper.writeValueAsString(site);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-disposition", "attachment; filename=" + siteName + ".json");
        ResponseEntity respEntity = new ResponseEntity(json.getBytes(), responseHeaders, HttpStatus.OK);
        return respEntity;
    }

    @GetMapping("/site/{siteName}/{pathName}")
    public Page retrievePage(
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
            throw new RuntimeException("Unable to Find Page [" + pathName + "] for Site [" + siteName + "]");
        }
        return page;
    }
}
