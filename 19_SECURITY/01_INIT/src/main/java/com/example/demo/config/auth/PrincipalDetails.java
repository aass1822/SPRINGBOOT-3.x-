package com.example.demo.config.auth;

import com.example.demo.domain.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrincipalDetails implements UserDetails {
    private UserDto dto;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        //계정이 단일 ROLE을 가질때("ROLE_USER")
        //authorities.add(new SimpleGrantedAuthority(dto.getRole()));

        //계정이 여러 ROLE을 가질때("ROLE_ADMIN,ROLE_USER")
        String roles [] = dto.getRole().split(","); //["ROLE_ADMIN","ROLE_USER"]
        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return dto.getPassword();
    }

    @Override
    public String getUsername() {
        return dto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
