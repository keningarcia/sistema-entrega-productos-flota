package com.keningarcia.sistema_entrega_productos_flota.repository;

import com.keningarcia.sistema_entrega_productos_flota.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

}
