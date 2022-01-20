package co.com.sofka.pelisplus.domain.cine.event;

import co.com.sofka.pelisplus.domain.generic.DomainEvent;

import java.util.Set;

public class PeliculaAgregada extends DomainEvent {
    private String idCine;
    private String titulo;
    private String generos;
    private String annio;
    private String sinopsis;
    private String url;

    public PeliculaAgregada(String idCine, String titulo, String generos, String annio, String sinopsis, String url) {
        super("sofka.pelisplus.domain.cine.peliculaagregada");
        this.idCine = idCine;
        this.titulo = titulo;
        this.generos = generos;
        this.annio = annio;
        this.sinopsis = sinopsis;
        this.url = url;
    }

    public String getIdCine() {
        return idCine;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGeneros() {
        return generos;
    }

    public String getAnnio() {
        return annio;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getUrl() {
        return url;
    }
}
