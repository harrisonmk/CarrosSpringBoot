package com.projeto.CarrosSpringBoot.servico;

import com.projeto.CarrosSpringBoot.excecoes.ObjectNotFoundException;
import com.projeto.CarrosSpringBoot.modelo.Carro;
import com.projeto.CarrosSpringBoot.modelo.dto.CarroDto;
import com.projeto.CarrosSpringBoot.repositorio.CarroRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarroService {

    @Autowired
    private CarroRepositorio carroRepositorio;

    //Salva um carro no banco de dados
    public CarroDto salvar(Carro carro) {

        Assert.isNull(carro.getId(), "nao foi possivel inserir o registro");
        return CarroDto.create(carroRepositorio.save(carro));

    }

    //Metodo para editar Carro
    public CarroDto editar(Carro carro, Long id) {

        Assert.notNull(id, "nao foi possivel atualizar o registro");

        //Busca o carro no banco de dados
        Optional<Carro> optional = carroRepositorio.findById(id);
        if (optional.isPresent()) {
            Carro db = optional.get();

            //Copiar as propriedades
            db.setNome(carro.getNome());
            db.setDescricao(carro.getDescricao());
            db.setUrl_foto(carro.getUrl_foto());
            db.setUrl_video(carro.getUrl_video());
            db.setLatitude(carro.getLatitude());
            db.setLongitude(carro.getLongitude());
            db.setTipo(carro.getTipo());

            //Atualiza o carro
            carroRepositorio.save(db);

            return CarroDto.create(db);
        } else {
            return null;
            //throw new RuntimeException("nao foi possivel atualizar o registro");

        }

    }

    //Lista todos os carros por id
    public List<CarroDto> listagemCarros(Pageable pageable) {

        List<CarroDto> lista = carroRepositorio.findAll(pageable).stream().map(CarroDto::create).collect(Collectors.toList());

        return lista;

    }

    // Lista todos os carros por tipo
    public List<CarroDto> listagemCarrosporTipo(String tipo, Pageable pageable) {

        //carroRepositorio.findByTipo(tipo);
        return carroRepositorio.findByTipo(tipo, pageable).stream().map(CarroDto::create).collect(Collectors.toList()); //era new ficou create por causa do create

    }

    //busca um carro por id
    public CarroDto buscarCarroId(Long id) {

        return carroRepositorio.findById(id).map(CarroDto::create).orElseThrow(() -> new ObjectNotFoundException("Carro nao encontrado")); //mesmo que c-> new CarroDto(c);

    }

    //metodo para excluir carro por id
    public void excluir(Long id) {

        carroRepositorio.deleteById(id);

    }

    //Metodo para buscar de acordo com o nome do carro
    public List<CarroDto> search(String query) {
        return carroRepositorio.findByNomeContaining(query).stream().map(CarroDto::create).collect(Collectors.toList());
    }

    
    
}
