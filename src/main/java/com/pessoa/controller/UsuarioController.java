package com.pessoa.controller;

import com.pessoa.exception.SenhaInvalidaException;
import com.pessoa.model.dto.CredenciaisDto;
import com.pessoa.model.dto.ErrorMessage;
import com.pessoa.model.dto.TokenDto;
import com.pessoa.model.entiy.Usuario;
import com.pessoa.security.JwtService;
import com.pessoa.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/apiPessoa/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    Validator validator;

    @PostMapping
    public ResponseEntity<List<String>> cadastraUsuario(@RequestBody Usuario usuario){

        List<String> erros = validator.validate(usuario).stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        if (erros.size() > 0) {
            return ResponseEntity.badRequest().body(erros);
        } else {
            String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);
            usuarioService.salvar(usuario);
            return ResponseEntity.ok().body(Arrays.asList("Usuário cadastrado!"));
        }
    }

    @PostMapping("/auth")
    public TokenDto autenticaUsuario(@RequestBody CredenciaisDto credenciaisDto){

        try {
            Usuario usuario = new Usuario();
            usuario.setLogin(credenciaisDto.getLogin());
            usuario.setSenha(credenciaisDto.getSenha());
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.geraToken(usuario);
            return new TokenDto(usuario.getLogin(), token);

        } catch (UsernameNotFoundException | SenhaInvalidaException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
