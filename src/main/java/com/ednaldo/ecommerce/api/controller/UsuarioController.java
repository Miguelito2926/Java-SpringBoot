package com.ednaldo.ecommerce.api.controller;

import com.ednaldo.ecommerce.domain.dto.CredenciaisDTO;
import com.ednaldo.ecommerce.domain.dto.TokenDTO;
import com.ednaldo.ecommerce.domain.entity.Usuario;
import com.ednaldo.ecommerce.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/usuarios")
@Api("Api Usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Criar um novo usuário")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Usuário criado com sucesso."),
            @ApiResponse(code = 400, message = "Erro de validação.")
    })
    public Usuario insert(@RequestBody @ApiParam("Informações do novo usuário") @Valid Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioService.createUsuario(usuario);
    }

    @PostMapping(value = "/auth")
    @ResponseStatus(CREATED)
    @ApiOperation("Autenticar usuário e gerar token")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Token gerado com sucesso."),
            @ApiResponse(code = 400, message = "Credenciais inválidas.")
    })
    public TokenDTO getToken(@RequestBody @ApiParam("Credenciais do usuário") CredenciaisDTO credenciaisDTO) {
        return usuarioService.autenticar(credenciaisDTO);
    }
}

