package com.projeto.CarrosSpringBoot.servico;

import com.projeto.CarrosSpringBoot.modelo.dto.UsuarioDto;
import com.projeto.CarrosSpringBoot.repositorio.UsuarioRepositorio;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio rep;

    public List<UsuarioDto> getUsers() {
        return rep.findAll().stream().map(UsuarioDto::create).collect(Collectors.toList());
    }

}
