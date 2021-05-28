package pl.edu.wszib.forum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.forum.database.TopicRepository;
import pl.edu.wszib.forum.database.entity.TopicEntity;
import pl.edu.wszib.forum.domain.Topic;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final UserService userService;

    public List<Topic> getAll() {
        return topicRepository.findAll().stream()
                .map(TopicService::mapEntityToTopic)
                .collect(Collectors.toList());
    }

    public Optional<Topic> getById(final UUID id) {
        return topicRepository.findById(id)
                .map(TopicService::mapEntityToTopic);
    }

    public UUID add(final Topic topic) {
        final UUID id = UUID.randomUUID();

        topicRepository.save(mapTopicToEntity(id, topic));

        return id;
    }

    public void update(final Topic topic) {
        topicRepository.save(mapTopicToEntity(topic.getId(), topic));
    }

    public void remove(final UUID id) {
        topicRepository.deleteById(id);
    }

    private static Topic mapEntityToTopic(final TopicEntity entity) {
        return Topic.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .userId(UserService.mapEntityToUser(entity.getUserEntity()).getId())
                .build();
    }

    public TopicEntity mapTopicToEntity(final UUID id, final Topic topic) {
        return TopicEntity.builder()
                .id(id)
                .title(topic.getTitle())
                .content(topic.getContent())
                .userEntity(UserService.mapUserToEntity(topic.getUserId(), userService.getById(topic.getUserId()).get()))
                .build();
    }
}
