package pl.edu.wszib.forum.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    @Id
    private UUID id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postentity_id", referencedColumnName = "id")
    private PostEntity postEntity;
}
