package com.example.googleLogin.controller;

import com.example.googleLogin.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class HomeController {

    private final AuthService authService;

    public HomeController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/login-success")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        authService.saveOrUpdateUser(email);

        return "Login successful, user saved to the database!";
    }

    @GetMapping("/login-failure")
    public String loginFailure() {
        return "Login failed.";
    }
}
