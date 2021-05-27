package pl.edu.wszib.forum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.forum.database.CommentRepository;
import pl.edu.wszib.forum.database.entity.CommentEntity;
import pl.edu.wszib.forum.domain.Comment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;

    public List<Comment> getAll() {
        return commentRepository.findAll().stream()
                .map(CommentService::mapEntityToComment)
                .collect(Collectors.toList());
    }

    public Optional<Comment> getById(final UUID id) {
        return commentRepository.findById(id)
                .map(CommentService::mapEntityToComment);
    }

    public UUID add(final Comment comment) {
        final UUID id = UUID.randomUUID();

        commentRepository.save(mapCommentToEntity(id, comment));

        return id;
    }

    public void update(final Comment comment) {
        commentRepository.save(mapCommentToEntity(comment.getId(), comment));
    }

    public void remove(final UUID id) {
        commentRepository.deleteById(id);
    }

    private static Comment mapEntityToComment(final CommentEntity entity) {
        return Comment.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .userId(entity.getUserEntity().getId())
                //.postId(entity.get)
                .build();
    }

    private CommentEntity mapCommentToEntity(final UUID id, final Comment comment) {
        return CommentEntity.builder()
                .id(id)
                .title(comment.getTitle())
                .content(comment.getContent())
                .userEntity(UserService.mapUserToEntity(comment.getUserId(), userService.getById(comment.getUserId()).get()))
                .build();
    }

}
