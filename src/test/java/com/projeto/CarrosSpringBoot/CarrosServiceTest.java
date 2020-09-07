package com.projeto.CarrosSpringBoot;

import com.projeto.CarrosSpringBoot.excecoes.ObjectNotFoundException;
import com.projeto.CarrosSpringBoot.modelo.Carro;
import com.projeto.CarrosSpringBoot.modelo.dto.CarroDto;
import com.projeto.CarrosSpringBoot.servico.CarroService;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class CarrosServiceTest {

    @Autowired
    private CarroService carroService;

    @Test
    void text1() {

        Carro carro = new Carro();
        carro.setNome("ferrari");
        carro.setTipo("esportivos");

        CarroDto c = carroService.salvar(carro);

        assertNotNull(c);
        Long id = c.getId();
        assertNotNull(id);

        //Buscar o objeto
        CarroDto op = carroService.buscarCarroId(id);
        
        assertNotNull(op);        

        
        assertEquals("ferrari", c.getNome());
        assertEquals("esportivos", c.getTipo());

        //deletar o objeto
        carroService.excluir(id);

        //verificar se deletou
        try{
            assertNull(carroService.buscarCarroId(id));
        }catch(ObjectNotFoundException e){
            
        }

    }

    @Test
    public void testLista() {

        List<CarroDto> carros = carroService.listagemCarros(PageRequest.of(0,30));

        assertEquals(30, carros.size());

    }

    @Test
    public void testListaPorTipo() {

        assertEquals(10, carroService.listagemCarrosporTipo("classicos",PageRequest.of(0, 10)).size());
        assertEquals(10, carroService.listagemCarrosporTipo("esportivos",PageRequest.of(0, 10)).size());
        assertEquals(10, carroService.listagemCarrosporTipo("luxo",PageRequest.of(0, 10)).size());

        assertEquals(0, carroService.listagemCarrosporTipo("x",PageRequest.of(0, 10)).size());
    }

    @Test
    public void testGet() {

        CarroDto op = carroService.buscarCarroId(3L);

        assertNotNull(op);   

        assertEquals("Tucker 1948", op.getNome());

    }

}
