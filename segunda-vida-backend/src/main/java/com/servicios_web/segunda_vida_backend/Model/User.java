package com.servicios_web.segunda_vida_backend.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    @JsonProperty("id_usuario")
    private int idUser;

    @NotNull(message = "Name cannot be null.")
    @NotBlank(message = "Name cannot be null or empty and must contain at least one non-whitespace character.")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters.")
    @Column(name = "nombre_completo")
    @JsonProperty("nombre_completo")
    private String name;

    @NotNull(message = "Username cannot be null.")
    @NotBlank(message = "Username cannot be null or empty and must contain at least one non-whitespace character.")
    @Size(min = 8, max = 50, message = "Username must be between 8 and 50 characters.")
    @Column(name = "nombre_usuario")
    @JsonProperty("nombre_usuario")
    private String userName;

    @NotNull(message = "Password cannot be null.")
    @NotBlank(message = "Password cannot be null or empty and must contain at least one non-whitespace character.")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters.")
    @Column(name = "contrasena")
    @JsonProperty("contrasena")
    private String password;

    @NotNull(message = "Phone number cannot be null.")
    @NotBlank(message = "Phone number cannot be null or empty and must contain at least one non-whitespace character.")
    @Size(min = 10, max = 20, message = "Phone number must be between 10 and 20 characters.")
    @Column(name = "telefono")
    @JsonProperty("telefono")
    private String phone;

    @NotNull(message = "Email cannot be null.")
    @NotBlank(message = "Email cannot be null or empty and must contain at least one non-whitespace character.")
    @Size(min = 10, max = 100, message = "Email must be between 10 and 100 characters.")
    @Column(name = "correo_electronico")
    @JsonProperty("correo_electronico")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Shopping> purchases = new ArrayList<>();

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userName));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;    }

    @Override
    public boolean isEnabled() {
        return true;    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


