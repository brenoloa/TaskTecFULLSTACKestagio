package com.example.login_auth_api.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login_auth_api.domain.user.User;
import com.example.login_auth_api.dto.LoginRequestDTO;
import com.example.login_auth_api.dto.RegisterRequestDTO;
import com.example.login_auth_api.dto.ResponseDTO;
import com.example.login_auth_api.infra.security.TokenService;
import com.example.login_auth_api.repositories.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(UserRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {
        try {
            User user = repository.findByEmail(body.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("user não encontrado"));

            if (passwordEncoder.matches(body.getPassword(), user.getPassword())) {
                String token = tokenService.generateToken(user);
                return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
            } else {
                return ResponseEntity.badRequest().body("Senha invalida.");
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body("user não encontrado.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        Optional<User> user = repository.findByEmail(body.getEmail());

        if (user.isPresent()) {
            return ResponseEntity.badRequest().body(new ResponseDTO("E-mail já registrado.", null));
        }

        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(body.getPassword()));
        newUser.setEmail(body.getEmail());
        newUser.setName(body.getName());
        repository.save(newUser);

        String token = tokenService.generateToken(newUser);
        return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
    }
}
