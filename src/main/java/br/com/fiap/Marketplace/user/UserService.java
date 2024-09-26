package br.com.fiap.Marketplace.user;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User updateUser(UUID id, User user) {
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating Not Found"));
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating Not Found"));
        userRepository.deleteById(id);
    }
}
