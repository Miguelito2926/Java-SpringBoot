package com.ednaldo.ecommerce.service;

import com.ednaldo.ecommerce.domain.entity.Usuario;
import com.ednaldo.ecommerce.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final PasswordEncoder encoder;
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

       Usuario usuario =  usuarioRepository.findByLogin(userName)
               .orElseThrow( () -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

       String [] roles = usuario.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};

       return User.builder()
               .username(usuario.getLogin())
               .password(usuario.getSenha())
               .roles(roles)
               .build();
    }

    @Transactional
    public Usuario createUsuario(Usuario usuario) {
      return usuarioRepository.save(usuario);

    }
}
