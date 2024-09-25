package br.com.fiap.Marketplace.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    UUID id;
    @NotNull
    String name;
    @Column(unique = true)
    @Email
    String email;
    @NotNull
    @Size(min = 6)
    String password;

}
