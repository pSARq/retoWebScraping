package co.com.sofka.pelisplus.domain.cine;

import java.util.Objects;

public final class Pelicula {

    private final String id;
    private String titulo;
    private String generos;
    private String annio;
    private String sinopsis;
    private String url;

    public Pelicula(String id, String titulo, String generos, String annio, String sinopsis, String url) {
        this.id = id;
        this.titulo = Objects.requireNonNull(titulo);
        this.generos = Objects.requireNonNull(generos);
        this.annio = Objects.requireNonNull(annio);
        this.sinopsis = Objects.requireNonNull(sinopsis);
        this.url = Objects.requireNonNull(url);
    }

    public void modificarTitulo(String titulo) {
        this.titulo = Objects.requireNonNull(titulo);
    }

    public void modificarGeneros(String generos) {
        this.generos = Objects.requireNonNull(generos);
    }

    public void modificarAnnio(String annio) {
        this.annio = Objects.requireNonNull(annio);
    }

    public void modificarSinopsis(String sinopsis) {
        this.sinopsis = Objects.requireNonNull(sinopsis);
    }

    public void modificarUrl(String url) {
        this.url = Objects.requireNonNull(url);
    }

    public String id() {
        return id;
    }

    public String titulo() {
        return titulo;
    }

    public String generos() {
        return generos;
    }

    public String annio() {
        return annio;
    }

    public String sinopsis() {
        return sinopsis;
    }

    public String url() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(id, pelicula.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
