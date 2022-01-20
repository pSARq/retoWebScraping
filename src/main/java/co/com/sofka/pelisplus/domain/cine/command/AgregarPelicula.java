package co.com.sofka.pelisplus.domain.cine.command;

import co.com.sofka.pelisplus.domain.generic.Command;

public class AgregarPelicula extends Command {

    private String idCine;
    private String idPelicula;
    private String titulo;
    private String generos;
    private String annio;
    private String sinopsis;
    private String url;

    public AgregarPelicula() {
    }

    public String getIdCine() {
        return idCine;
    }

    public void setIdCine(String idCine) {
        this.idCine = idCine;
    }

    public String getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }

    public String getAnnio() {
        return annio;
    }

    public void setAnnio(String annio) {
        this.annio = annio;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
