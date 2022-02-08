package com.devpedrod.registrationlogin.model;

import com.devpedrod.registrationlogin.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "_user")
@Data
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    public User(){
        userRole = UserRole.USER;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
