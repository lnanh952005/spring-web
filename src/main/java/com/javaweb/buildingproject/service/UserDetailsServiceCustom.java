package com.javaweb.buildingproject.service.impl;

import com.javaweb.buildingproject.domain.dto.UserDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

//@Component("userDetailsService")
@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    private UserService userService;

    public UserDetailsServiceCustom(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userService.fetchUserByUserName(username);
        if(userDTO == null){
            throw new UsernameNotFoundException("tk hoặc mk không hợp lệ");
        }
        return new User(
                userDTO.getUsername(),
                userDTO.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("user_role"))
        );
    }
}
