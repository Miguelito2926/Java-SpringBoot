package com.ednaldo.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

       if(!userName.equals("Miguel")) {
           throw new UsernameNotFoundException("Usuário não encontrado na base.");
       }
        return User.builder()
                .username("Miguel")
                .password(encoder.encode("123"))
                .roles("USER", "ADMIN")
                .build();
    }
}
