package com.keningarcia.sistema_entrega_productos_flota.service;

import com.keningarcia.sistema_entrega_productos_flota.dto.UsuarioDTO;
import com.keningarcia.sistema_entrega_productos_flota.entity.User;
import com.keningarcia.sistema_entrega_productos_flota.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioDTO create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        if (user.getRole() == null) user.setRole("CONDUCTOR");
        return toDTO(userRepository.save(user));
    }

    public List<UsuarioDTO> findAll() {
        return userRepository.findAll().stream().map(this::toDTO).toList();
    }

    public UsuarioDTO findById(Long id) {
        return toDTO(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private UsuarioDTO toDTO(User user) {
        return new UsuarioDTO(user.getId(), user.getUsername(), user.getNombre(),
                user.getApellido(), user.getEmail(), user.getRole());
    }
}
