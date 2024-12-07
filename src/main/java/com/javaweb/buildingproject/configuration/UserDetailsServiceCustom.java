package com.javaweb.buildingproject.configuration;

import com.javaweb.buildingproject.domain.entity.UserEntity;
import com.javaweb.buildingproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class UserDetailsServiceCustom implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceCustom( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found from loaduserbyusername"));
//        return new User(
//                userEntity.getUsername(),
//                userEntity.getPassword(),
//                Collections.singletonList(new SimpleGrantedAuthority("user_role"))
//        );

        return new UserDetailsCustom(userEntity);
    }

    @Getter
    @Setter
    public class UserDetailsCustom implements UserDetails {
        private UserEntity userEntity;

        public UserDetailsCustom(UserEntity userEntity) {
            this.userEntity = userEntity;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.singletonList(new SimpleGrantedAuthority("user_role"));
        }

        @Override
        public String getPassword() {
            return userEntity.getPassword();
        }

        @Override
        public String getUsername() {
            return userEntity.getUsername();
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
}