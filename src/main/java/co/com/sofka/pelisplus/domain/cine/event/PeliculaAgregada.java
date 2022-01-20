package co.com.sofka.pelisplus.domain.cine.event;

import co.com.sofka.pelisplus.domain.generic.DomainEvent;

public class PeliculaAgregada extends DomainEvent {
    private String idPelicula;
    private String titulo;
    private String generos;
    private String annio;
    private String sinopsis;
    private String url;

    public PeliculaAgregada(String idPelicula, String titulo, String generos, String annio, String sinopsis, String url) {
        super("sofka.cine.peliculaagregada");
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.generos = generos;
        this.annio = annio;
        this.sinopsis = sinopsis;
        this.url = url;
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

    public String getIdPelicula() {
        return idPelicula;
    }
}
