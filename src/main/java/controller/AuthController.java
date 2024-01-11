package controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dao.UserJpaSpring;
import models.AuthResponse;
import models.User;
import models.UserLoginRequest;

@RestController
public class AuthController {

    @Autowired
    private UserJpaSpring userRepository;

    @PostMapping("/api/login")
    public AuthResponse login(@RequestBody UserLoginRequest userLoginRequest) {
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();

        User user = userRepository.findByEmailAndPassword(email, password);

        if (user != null) {
            return new AuthResponse("Login exitoso", user);
        } else {
            return new AuthResponse("Credenciales inválidas", null);
        }
    }
}
