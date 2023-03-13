package com.kalidratorma.cms.core.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> readUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping
    public HttpStatus createUser(
            @RequestBody User user) {
        userRepository.save(user);
        return HttpStatus.CREATED;
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> readUser(
            @PathVariable String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow();
        return ResponseEntity.ok(user);
    }
    
    @PutMapping
    public HttpStatus updateUser(
            @RequestBody User user) {
        User origUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow();
        user.setId(origUser.getId());
        userRepository.save(user);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{userName}")
    public HttpStatus deleteUser(
            @PathVariable String userName) {
        User origUser = userRepository.findByUsername(userName)
                .orElseThrow();
        userRepository.deleteById(origUser.getId());
        return HttpStatus.OK;
    }

}
