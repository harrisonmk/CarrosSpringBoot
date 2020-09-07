
package com.projeto.CarrosSpringBoot.repositorio;

import com.projeto.CarrosSpringBoot.modelo.Carro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarroRepositorio extends JpaRepository<Carro,Long> {
    
    List<Carro> findByTipo(String tipo);
    
}
