package pl.edu.wszib.forum.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.forum.domain.User;
import pl.edu.wszib.forum.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getById(@PathVariable final UUID id) {
        return userService.getById(id);
    }

    //@PostMapping(path = "/add", consumes = "application/json",  produces = "application/json")
    @PostMapping
    public UUID add(@RequestBody final User user) {
        System.out.println("Adding User="+user);
        return userService.add(user);
    }

    @PutMapping
    public void update(@RequestBody final User user) {
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable final UUID id) {
        userService.remove(id);
    }
}
