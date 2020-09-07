package com.projeto.CarrosSpringBoot.modelo.dto;

import com.projeto.CarrosSpringBoot.modelo.Carro;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CarroDto {

    private Long id;
    private String nome;
    String descricao;
    String url_foto;
    String url_video;
    String latitude;
    String longitude;
    String tipo;

    /*  public CarroDto(Carro carro) {
        
      this.id = carro.getId();
      this.nome = carro.getNome();
      this.descricao = carro.getDescricao();
      this.url_foto = carro.getUrl_foto();
      this.url_video = carro.getUrl_video();
      this.latitude = carro.getLatitude();
      this.longitude = carro.getLongitude();
      this.tipo = carro.getTipo();
        
        
    } */
    public static CarroDto create(Carro carro) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDto.class);
    }


    
    
}
