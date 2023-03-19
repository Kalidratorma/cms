package com.kalidratorma.cms.core.site;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.kalidratorma.cms.core.security.user.Role;
import com.kalidratorma.cms.core.security.user.User;
import com.kalidratorma.cms.core.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kalidratorma.cms.core.utils.ControllerUtils.getFilteredMapper;

@RestController
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
public class SiteController {

    private final SiteRepository siteRepository;

    private final UserRepository userRepository;

    @GetMapping("/site")
    public MappingJacksonValue readSites() {
        List<Site> siteList = siteRepository.findAll();
        return getFilteredMapper(siteList, "SiteFilter",
                SimpleBeanPropertyFilter.serializeAllExcept("pageList"));
    }

    @PostMapping("/site")
    public ResponseEntity<String> createSite(@RequestBody Site site) {
        siteRepository.save(site);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/site/{siteName}")
    public Site readSite(@PathVariable String siteName) {
        return siteRepository.findByName(siteName).orElseThrow();
    }

    @PutMapping("/site")
    public ResponseEntity<String> updateSite(@RequestBody Site site) {
        Site origSite = siteRepository.findByName(site.getName()).orElseThrow();
        User user = userRepository
                .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        ResponseEntity<String> response;
        if (user.getAuthorities().contains(new SimpleGrantedAuthority(Role.ADMIN.name()))
                || user.getSites().contains(origSite)) {
            site.setId(origSite.getId());
            siteRepository.save(site);
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return response;
    }

    @DeleteMapping("/site/{siteName}")
    public ResponseEntity<String> deleteSite(@PathVariable String siteName) {
        Site origSite = siteRepository.findByName(siteName).orElseThrow();
        siteRepository.deleteById(origSite.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/siteAsFile/{siteName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody ResponseEntity<MappingJacksonValue> getSiteAsFile(@PathVariable String siteName) {
        Site site = siteRepository.findByName(siteName).orElseThrow();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-disposition"
                , "attachment; filename=" + siteName + ".json");
        responseHeaders.add("content-type"
                , "application/json");
        return new ResponseEntity<>(getFilteredMapper(site, "SiteFilter"
                    , SimpleBeanPropertyFilter.serializeAll())
                , responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/site/{siteName}/{pathName}")
    public Page retrievePage(@PathVariable String siteName, @PathVariable String pathName) {
        Site site = siteRepository.findByName(siteName).orElseThrow();
        return site.getPageList().stream()
                .filter(page1 -> pathName.equals(page1.getPathName())).findAny().orElseThrow();
    }
}
