package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.User;
import br.com.inventory.mechanicalparts.entities.enums.EnumRole;
import br.com.inventory.mechanicalparts.repositories.RoleRepository;
import br.com.inventory.mechanicalparts.repositories.UserRepository;
import br.com.inventory.mechanicalparts.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(String typeUser, String emailUser) {
        User user = new User();
//        Role role = new Role();
//        role.setName(defineEnum(typeUser));
//        roleRepository.save(role);
//        user.setRoles(Collections.singleton(role));
//        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        user.setPassword(generatePassword(emailUser));
        user.setLogin(emailUser);

        return userRepository.save(user);
//        role.setUsers(Collections.singleton(savedUser));
//        roleRepository.save(role);
//        return savedUser;
    }

    private String defineEnum(String typeUser) {
        return typeUser.equals("professional") ? EnumRole.ROLE_ADMIN.getDescription() : EnumRole.ROLE_USER.getDescription();
    }

    @Override
    public void updateChangePassword(String login, String password, String newPassword) {
        User newUser = findByPasswordAndLogin(password, login);

        if (Util.isEmpty(newUser)) {
            return;
        }

        newUser.setPassword(generatePassword(newPassword));
        userRepository.save(newUser);
    }

    @Override
    public String generatePassword(String secretKey) {
        return passwordEncoder.encode(secretKey);
    }

    @Override
    public User findByPasswordAndLogin(String token, String username) {
        return userRepository.findByPasswordAndLogin(token, username);
    }

    @Override
    public JpaRepository<User, Long> getRepository() {
        return null;
    }


    public static void main(String[] args) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        passwordEncoder.encode("123");
    }
}
