package com.projeto.CarrosSpringBoot.controle;

import com.projeto.CarrosSpringBoot.modelo.Usuario;
import com.projeto.CarrosSpringBoot.modelo.dto.UsuarioDto;
import com.projeto.CarrosSpringBoot.servico.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UsuarioControle {

    @Autowired
    private UsuarioService service;

    @GetMapping()
    public ResponseEntity get() {
        List<UsuarioDto> list = service.getUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/info")
    public UsuarioDto userInfo(@AuthenticationPrincipal Usuario user) {

        //UserDetails userDetails = JwtUtil.getUserDetails();
        return UsuarioDto.create(user);
    }

}
