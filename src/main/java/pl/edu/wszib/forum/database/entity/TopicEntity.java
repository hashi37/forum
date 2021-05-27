package pl.edu.wszib.forum.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicEntity {
    @Id
    private String id;
    private String title;
    private String content;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PostEntity> posts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;
}
