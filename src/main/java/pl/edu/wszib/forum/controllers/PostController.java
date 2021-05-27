package pl.edu.wszib.forum.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.forum.domain.Post;
import pl.edu.wszib.forum.service.PostService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<Post> getAll() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Post> getById(@PathVariable final UUID id) {
        return postService.getById(id);
    }

    @PostMapping
    public UUID add(@RequestBody final Post post) {
        return postService.add(post);
    }

    @PutMapping
    public void update(@RequestBody final Post post) {
        postService.update(post);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable final UUID id) {
        postService.remove(id);
    }

}
