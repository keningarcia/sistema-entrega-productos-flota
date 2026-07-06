package com.keningarcia.sistema_entrega_productos_flota.controller;

import com.keningarcia.sistema_entrega_productos_flota.config.JwtTokenProvider;
import com.keningarcia.sistema_entrega_productos_flota.dto.LoginDTO;
import com.keningarcia.sistema_entrega_productos_flota.dto.LoginResponseDTO;
import com.keningarcia.sistema_entrega_productos_flota.dto.UsuarioDTO;
import com.keningarcia.sistema_entrega_productos_flota.entity.User;
import com.keningarcia.sistema_entrega_productos_flota.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        String username = auth.getName();
        String role = auth.getAuthorities().stream()
                .findFirst()
                .map(g -> g.getAuthority().replace("ROLE_", ""))
                .orElse("CONDUCTOR");

        String token = jwtTokenProvider.generateToken(username, role);
        return ResponseEntity.ok(new LoginResponseDTO(token, username, role));
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> register(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }
}
