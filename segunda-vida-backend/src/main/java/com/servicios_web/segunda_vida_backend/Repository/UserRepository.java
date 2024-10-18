package com.servicios_web.segunda_vida_backend.Repository;

import com.servicios_web.segunda_vida_backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Encontrar un usuario por su nombre de usuario usando JPQL
    @Query("SELECT u FROM User u WHERE u.userName = :userName")
    Optional<User> findByUserNameJPQL(@Param("userName") String userName);

    // Encontrar un usuario por su correo electrónico usando JPQL
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmailJPQL(@Param("email") String email);

    // Encontrar un usuario por su nombre de completo usando JPQL
    @Query("SELECT u FROM User u WHERE u.name = :name")
    Optional<User> findByNameJPQL(@Param("name") String name);
}
