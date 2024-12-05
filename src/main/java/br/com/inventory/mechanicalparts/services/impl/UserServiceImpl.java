package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.Role;
import br.com.inventory.mechanicalparts.entities.User;
import br.com.inventory.mechanicalparts.entities.enums.EnumRole;
import br.com.inventory.mechanicalparts.exceptions.BadRequestException;
import br.com.inventory.mechanicalparts.repositories.RoleRepository;
import br.com.inventory.mechanicalparts.repositories.UserRepository;
import br.com.inventory.mechanicalparts.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(String emailUser, EnumRole enumRole) {
        User user = new User();
        Optional<Role> role = roleRepository.findByName(enumRole.name());

        if (Util.isEmpty(role)) {
            throw new BadRequestException("Usuário sem definição de permissão.");
        }

        user.setPassword(generatePassword(emailUser));
        user.setLogin(emailUser);
        user.setRole(role.get());
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
