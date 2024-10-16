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
}
