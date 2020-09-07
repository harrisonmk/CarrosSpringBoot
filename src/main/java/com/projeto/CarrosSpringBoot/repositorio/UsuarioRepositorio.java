
package com.projeto.CarrosSpringBoot.repositorio;

import com.projeto.CarrosSpringBoot.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
    
    Usuario findByLogin(String login);
    
}
