package com.projeto.CarrosSpringBoot.modelo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.CarrosSpringBoot.modelo.Role;
import com.projeto.CarrosSpringBoot.modelo.Usuario;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDto {

    private String login;
    private String nome;
    private String email;

    // token jwt
    private String token;

    private List<String> roles;

    public static UsuarioDto create(Usuario user) {
        ModelMapper modelMapper = new ModelMapper();
        UsuarioDto dto = modelMapper.map(user, UsuarioDto.class);

        dto.roles = user.getRoles().stream()
                .map(Role::getNome)
                .collect(Collectors.toList());

        return dto;
    }

    public static UsuarioDto create(Usuario user, String token) {
        ModelMapper modelMapper = new ModelMapper();
        UsuarioDto dto = modelMapper.map(user, UsuarioDto.class); //preenche o dto com as informacoes do usuario
        dto.token = token;
        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    
    
}
