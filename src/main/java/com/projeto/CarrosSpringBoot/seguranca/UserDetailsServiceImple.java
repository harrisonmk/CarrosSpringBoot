package com.projeto.CarrosSpringBoot.seguranca;

import com.projeto.CarrosSpringBoot.modelo.Usuario;
import com.projeto.CarrosSpringBoot.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value="userDetailsService")
public class UserDetailsServiceImple implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        Usuario usuario = usuarioRepositorio.findByLogin(username);
        if(usuario == null){
            throw new UsernameNotFoundException("user not found");
        }

         return usuario;
        

        
    }

}
