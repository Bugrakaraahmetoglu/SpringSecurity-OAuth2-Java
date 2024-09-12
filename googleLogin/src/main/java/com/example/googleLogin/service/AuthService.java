package com.example.googleLogin.service;

import com.example.googleLogin.entity.User;
import com.example.googleLogin.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService  extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveOrUpdateUser(String email) {
        // Kullanıcı veritabanında yoksa kaydet
        userRepository.findByEmail(email).orElseGet(() -> {
            User user = new User(email);
            return userRepository.save(user);
        });
    }
}

