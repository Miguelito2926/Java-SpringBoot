package com.ednaldo.ecommerce.service;

import com.ednaldo.ecommerce.domain.dto.CredenciaisDTO;
import com.ednaldo.ecommerce.domain.dto.TokenDTO;
import com.ednaldo.ecommerce.domain.entity.Usuario;
import com.ednaldo.ecommerce.domain.repository.UsuarioRepository;
import com.ednaldo.ecommerce.exception.PasswordInvalidException;
import com.ednaldo.ecommerce.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final PasswordEncoder encoder;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public UserDetails autenticar(Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhaMatch = encoder.matches(usuario.getSenha(), user.getPassword());
        if (senhaMatch){
            return  user;
        }
        throw new PasswordInvalidException();
    }

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

    public TokenDTO autenticar(CredenciaisDTO credenciaisDTO) {
        try {
            Usuario usuario = Usuario.builder()
                    .login(credenciaisDTO.getLogin())
                    .senha(credenciaisDTO.getSenha())
                    .build();

            UserDetails usuarioAutenticado = autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);

        } catch (UsernameNotFoundException | PasswordInvalidException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
