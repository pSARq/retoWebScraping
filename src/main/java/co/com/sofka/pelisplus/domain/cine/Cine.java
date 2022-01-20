package co.com.sofka.pelisplus.domain.cine;

import co.com.sofka.pelisplus.domain.cine.event.CineCreado;
import co.com.sofka.pelisplus.domain.cine.event.PeliculaAgregada;
import co.com.sofka.pelisplus.domain.generic.AggregateRoot;
import co.com.sofka.pelisplus.domain.generic.DomainEvent;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cine extends AggregateRoot {

    protected Map<String, Pelicula> peliculas;
    protected String nombre;

    public Cine(String id, String nombre) {
        super(id);
        subscribe(new CineEventChange(this));
        appendChange(new CineCreado(nombre)).apply();
    }

    private Cine(String id){
        super(id);
        subscribe(new CineEventChange(this));
    }

    public static Cine from(String idCine, List<DomainEvent> events) {
        var cine = new Cine(idCine);
        events.forEach(cine::applyEvent);
        return cine;
    }

    public void agregarPelicula(String id, String titulo, String generos, String annio, String sinopsis, String url){
        this.peliculas.put(id, new Pelicula(id, titulo, generos, annio, sinopsis, url));
        appendChange(new PeliculaAgregada(id, titulo, generos, annio, sinopsis, url)).apply();
    }

    public void modificarTitulo(String id, String titulo) {
        this.peliculas.get(id).modificarTitulo(titulo);
    }

    public void modificarGeneros(String id, String generos) {
        this.peliculas.get(id).modificarGeneros(generos);
    }

    public void modificarAnnio(String id, String annio) {
        this.peliculas.get(id).modificarAnnio(annio);
    }

    public void modificarSinopsis(String id, String sinopsis){
        this.peliculas.get(id).modificarSinopsis(sinopsis);
    }

    public void modificarUrl(String id, String url) {
        this.peliculas.get(id).modificarUrl(url);
    }


}

