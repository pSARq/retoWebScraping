package co.com.sofka.pelisplus.domain.cine;

import co.com.sofka.pelisplus.domain.cine.event.CineCreado;
import co.com.sofka.pelisplus.domain.cine.event.PeliculaAgregada;
import co.com.sofka.pelisplus.domain.generic.EventChange;

import java.util.HashMap;

public class CineEventChange implements EventChange {

    protected CineEventChange(Cine cine) {
        listener((CineCreado event) -> {
            cine.nombre = event.getNombre();
            cine.peliculas = new HashMap<>();
        });
        listener((PeliculaAgregada event) -> {
            cine.peliculas.put(event.getIdCine(),
                    new Pelicula(
                            event.getIdCine(),
                            event.getTitulo(),
                            event.getGeneros(),
                            event.getAnnio(),
                            event.getSinopsis(),
                            event.getUrl()));
        });
    }
}
