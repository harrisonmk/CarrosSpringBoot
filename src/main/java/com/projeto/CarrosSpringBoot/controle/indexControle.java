package com.projeto.CarrosSpringBoot.controle;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class indexControle {

    @GetMapping
    public String get() {

        return "API Dos Carros";

    }

    //Metodo para veridicar as informacoes do usuario logado // passando essa uri e a senha e login no postman
    @GetMapping("/userInfo")
    public UserDetails userInfo(@AuthenticationPrincipal UserDetails user) {

        return user;
    }

}
