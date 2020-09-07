package com.projeto.CarrosSpringBoot.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Carro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 255) //campo nao obrigatorio | tamanho de 255 caracteres 
    private String nome;

    @Column(nullable = true, length = 255) //campo nao obrigatorio | tamanho de 255 caracteres 
    String descricao;

    @Column(nullable = true, length = 255) //campo nao obrigatorio | tamanho de 255 caracteres 
    String urlFoto;

    @Column(nullable = true, length = 255) //campo nao obrigatorio | tamanho de 255 caracteres 
    String urlVideo;

    @Column(nullable = true, length = 255) //campo nao obrigatorio | tamanho de 255 caracteres 
    String latitude;

    @Column(nullable = true, length = 255) //campo nao obrigatorio | tamanho de 255 caracteres 
    String longitude;

    @Column(nullable = true, length = 255) //campo nao obrigatorio | tamanho de 255 caracteres 
    String tipo;

    
    
    public Carro(Long id, String nome, String descricao, String urlFoto, String urlVideo, String latitude, String longitude, String tipo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.urlFoto = urlFoto;
        this.urlVideo = urlVideo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tipo = tipo;
    }

    
    
    public Carro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl_foto() {
        return urlFoto;
    }

    public void setUrl_foto(String url_foto) {
        this.urlFoto = url_foto;
    }

    public String getUrl_video() {
        return urlVideo;
    }

    public void setUrl_video(String url_video) {
        this.urlVideo = url_video;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    


    
    
    
}

