package net.QuinX.quinx.services;

import net.QuinX.quinx.entity.User;
import net.QuinX.quinx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // ✅ Injected properly

    // Save new user with encoded password and default role
    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // ✅ Encoded
        user.setRoles(Set.of("USER")); // You can customize or pass this dynamically
        userRepository.save(user);
    }

    // General save without encoding — use only if password is already encoded
    public void saveEntry(User user) {
        userRepository.save(user);
    }

    public List<User> getall() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
