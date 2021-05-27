package pl.edu.wszib.forum.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.forum.domain.Comment;
import pl.edu.wszib.forum.service.CommentService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Comment> getById(@PathVariable final UUID id) {
        return commentService.getById(id);
    }

    @PostMapping
    public UUID add(@RequestBody final Comment comment) {
        return commentService.add(comment);
    }

    @PutMapping
    public void update(@RequestBody final Comment comment) {
        commentService.update(comment);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable final UUID id) {
        commentService.remove(id);
    }
}
