package br.com.fiap.Marketplace.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
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
