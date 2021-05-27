package pl.edu.wszib.forum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.forum.database.PostRepository;
import pl.edu.wszib.forum.database.entity.PostEntity;
import pl.edu.wszib.forum.domain.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getAll() {
        return postRepository.findAll().stream()
                .map(PostService::mapEntityToPost)
                .collect(Collectors.toList());
    }

    public Optional<Post> getById(final UUID id) {
        return postRepository.findById(id)
                .map(PostService::mapEntityToPost);
    }

    public UUID add(final Post post) {
        final UUID id = UUID.randomUUID();

        postRepository.save(mapPostToEntity(id, post));

        return id;
    }

    public void update(final Post post) {
        postRepository.save(mapPostToEntity(post.getId(), post));
    }

    public void remove(final UUID id) {
        postRepository.deleteById(id);
    }

    private static Post mapEntityToPost(final PostEntity entity) {
        return Post.builder()
                .id(UUID.fromString(entity.getId()))
                .title(entity.getTitle())
                .content(entity.getContent())
                .user(UserService.mapEntityToUser(entity.getUserEntity()))
                .build();
    }

    private static PostEntity mapPostToEntity(final UUID id, final Post post) {
        return PostEntity.builder()
                .id(id.toString())
                .title(post.getTitle())
                .content(post.getContent())
                .userEntity(UserService.mapUserToEntity(post.getUser().getId(), post.getUser()))
                .build();
    }

}
