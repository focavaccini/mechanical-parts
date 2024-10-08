package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.entities.User;
import br.com.inventory.mechanicalparts.repositories.UserRepository;
import br.com.inventory.mechanicalparts.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(String emailUser) {
        User user = new User();

        user.setPassword(generatePassword(emailUser));
        user.setLogin(emailUser);
        return userRepository.save(user);
    }

    @Override
    public void updateChangePassword(String login, String password, String newPassword) {
        User newUser = findByPassword(password);
        newUser.setPassword(generatePassword(newPassword));
        userRepository.save(newUser);
    }

    @Override
    public String generatePassword(String secretKey) {
        return passwordEncoder.encode(secretKey);
    }

    @Override
    public User findByPassword(String token) {
        return userRepository.findByPassword(token);
    }

    @Override
    public JpaRepository<User, Long> getRepository() {
        return null;
    }
}
