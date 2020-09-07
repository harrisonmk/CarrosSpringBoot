package com.projeto.CarrosSpringBoot.controle;

import com.projeto.CarrosSpringBoot.modelo.Carro;
import com.projeto.CarrosSpringBoot.modelo.dto.CarroDto;
import com.projeto.CarrosSpringBoot.servico.CarroService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroControle {

    @Autowired
    private CarroService carroService;

    @PostMapping
    @Secured({"ROLE_ADMIN"}) //apenas usuario admin pode fazer insercao no banco
    public ResponseEntity salvarCarro(@RequestBody Carro carro) {

        CarroDto c = carroService.salvar(carro);
        URI location = getUri(c.getId());
        return ResponseEntity.created(location).build(); //retorna 201 created

    }

    
    
    //Pega a url do novo carro inserido
    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    
    
    @PutMapping("/{id}")
    public ResponseEntity editarCarro(@PathVariable("id") Long id, @RequestBody Carro carro) {

        carro.setId(id);
        CarroDto c = carroService.editar(carro, id);

        return c != null
                ? ResponseEntity.ok(c)
                : //retorna 200
                ResponseEntity.notFound().build(); //retorna 404

    }

    
    
    @DeleteMapping("/{id}")
    public ResponseEntity excluirCarro(@PathVariable("id") Long id) {
        carroService.excluir(id);

        return ResponseEntity.ok().build(); //retorna 200 
    }

    
    
    @GetMapping
    public ResponseEntity<List<CarroDto>> getCarros() {

        //return new ResponseEntity<>(carroService.listagemCarros(),HttpStatus.OK);
        return ResponseEntity.ok(carroService.listagemCarros()); //200 ok
    }

    
    @GetMapping("/{id}")
    public ResponseEntity getCarro(@PathVariable("id") Long id) {

        CarroDto carro = carroService.buscarCarroId(id);

        return ResponseEntity.ok(carro); //200 ok

    }

    
    
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CarroDto>> getCarroPorTipo(@PathVariable("tipo") String tipo) {

        List<CarroDto> carros = carroService.listagemCarrosporTipo(tipo);

        //se for vazio retorna 204 no content se nao retorna 200 ok
        return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
    }

    
    
}
