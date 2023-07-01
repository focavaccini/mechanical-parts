package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.entities.User;
import br.com.inventory.mechanicalparts.repositories.UserRepository;
import br.com.inventory.mechanicalparts.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
//        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
//        byte messageDigest[] = algorithm.digest(user.getPassword().getBytes("UTF-8"));
//
//        System.out.println(messageDigest);
    }

    @Override
    public JpaRepository<User, Long> getRepository() {
        return null;
    }
}
