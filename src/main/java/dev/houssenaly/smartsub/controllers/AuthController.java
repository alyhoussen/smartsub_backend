package dev.houssenaly.smartsub.controllers;

import dev.houssenaly.smartsub.models.User;
import dev.houssenaly.smartsub.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    // Endpoint d'inscription
    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String email = request.get("email");
        String password = request.get("password");

        User user = userService.registerUser(name, email, password);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Utilisateur créé avec succès");
        return response;
    }

    // Endpoint de connexion
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request, HttpSession session) {
        String email = request.get("email");
        String password = request.get("password");

        // Authentification via AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        // À la réussite, la session est créée et gérée automatiquement
        Map<String, String> response = new HashMap<>();
        response.put("message", "Connexion réussie");
        return response;
    }

    // Endpoint pour récupérer les informations de l'utilisateur connecté
    @GetMapping("/me")
    public Map<String, Object> me(Principal principal) {
        Map<String, Object> response = new HashMap<>();
        if (principal != null) {
            response.put("email", principal.getName());
        } else {
            response.put("email", null);
        }
        return response;
    }
}
