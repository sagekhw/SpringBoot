package com.sagekhw.authJwt.application1;

import com.sagekhw.authJwt.domain.User;
import com.sagekhw.authJwt.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    UserRepository userRepostory;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepostory,
                       PasswordEncoder passwordEncoder) {
        this.userRepostory = userRepostory;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(String email, String password) {
        User user = userRepostory.findByEmail(email)
                .orElseThrow(() -> new EmailNotExistedException(email));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordWrongException();
        }

        return user;
    }
}
