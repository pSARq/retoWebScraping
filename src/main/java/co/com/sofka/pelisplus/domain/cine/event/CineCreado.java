package co.com.sofka.pelisplus.domain.cine.event;

import co.com.sofka.pelisplus.domain.generic.DomainEvent;

public class CineCreado extends DomainEvent {

    private final String nombre;

    public CineCreado(String nombre) {
        super("sofka.cine.cinecreado");
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
