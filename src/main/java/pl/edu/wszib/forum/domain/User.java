package pl.edu.wszib.forum.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class User {
    UUID id;
    String username;
    String password;
    String email;
    LocalDate birthDate;
    boolean blocked;
}
