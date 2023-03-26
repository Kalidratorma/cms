package com.kalidratorma.cms.core.security.user;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.kalidratorma.cms.core.site.Site;
import com.kalidratorma.cms.core.site.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.kalidratorma.cms.core.utils.ControllerUtils.getFilteredMapper;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final SiteRepository siteRepository;

    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public MappingJacksonValue readUsers() {
        List<User> userList = userRepository.findAll();
        return getFilteredMapper(userList, "SiteFilter"
                , SimpleBeanPropertyFilter.filterOutAllExcept("id", "name"));
    }

    @PostMapping
    public HttpStatus createUser(@RequestBody User user) {
        userRepository.save(user);
        return HttpStatus.CREATED;
    }

    @GetMapping(value = "/{userName}")
    public MappingJacksonValue readUser(@PathVariable String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND
                        , "Username " + userName + " not found")
        );
        return getFilteredMapper(user, "SiteFilter"
                , SimpleBeanPropertyFilter.filterOutAllExcept("id", "name"));

    }

    @PutMapping
    public HttpStatus updateUser(@RequestBody User user) {
        User origUser = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND
                        , "Username " + user.getUsername() + " not found")
        );
        if (!user.getSites().isEmpty()) {
            List<Site> siteList = siteRepository
                    .findAllById(user.getSites().stream().map(Site::getId).toList());
            user.setSites(siteList);
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(origUser.getPassword());
        }
        user.setId(origUser.getId());
        userRepository.save(user);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{userName}")
    public HttpStatus deleteUser(@PathVariable String userName) {
        User origUser = userRepository.findByUsername(userName).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND
                        , "Username " + userName + " not found")
        );
        userRepository.deleteById(origUser.getId());
        return HttpStatus.OK;
    }
}
