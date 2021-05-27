package pl.edu.wszib.forum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.forum.database.UserRepository;
import pl.edu.wszib.forum.database.entity.UserEntity;
import pl.edu.wszib.forum.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll().stream()
                .map(UserService::mapEntityToUser)
                .collect(Collectors.toList());
    }

    public Optional<User> getById(final UUID id) {
        return userRepository.findById(id)
                .map(UserService::mapEntityToUser);
    }

    public UUID add(final User user) {
        final UUID id = UUID.randomUUID();

        userRepository.save(mapUserToEntity(id, user));

        return id;
    }

    public void update(final User user) {
        userRepository.save(mapUserToEntity(user.getId(), user));
    }

    public void remove(final UUID id) {
        userRepository.deleteById(id);
    }

    public static User mapEntityToUser(final UserEntity entity) {
        return User.builder()
                .id(UUID.fromString(entity.getId()))
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .birthDate(entity.getBirthDate())
                .blocked(entity.isBlocked())
                .build();
    }

    public static UserEntity mapUserToEntity(final UUID id, final User user) {
        return UserEntity.builder()
                .id(id.toString())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .blocked(user.isBlocked())
                .build();
    }
}
