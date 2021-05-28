package pl.edu.wszib.forum.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicEntity {
    @Id
    private UUID id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "topicEntity")
    private Set<PostEntity> postEntities;
}
