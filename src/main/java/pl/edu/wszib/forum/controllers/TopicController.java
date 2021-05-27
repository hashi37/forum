package pl.edu.wszib.forum.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.forum.domain.Topic;
import pl.edu.wszib.forum.service.TopicService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/topic")
@AllArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @GetMapping
    public List<Topic> getAll() {
        return topicService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Topic> getById(@PathVariable final UUID id) {
        return topicService.getById(id);
    }

    @PostMapping
    public UUID add(@RequestBody final Topic topic) {
        return topicService.add(topic);
    }

    @PutMapping
    public void update(@RequestBody final Topic topic) {
        topicService.update(topic);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable final UUID id) {
        topicService.remove(id);
    }

}
