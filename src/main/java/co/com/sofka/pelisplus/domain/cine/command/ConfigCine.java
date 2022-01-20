package co.com.sofka.pelisplus.domain.cine.command;

import co.com.sofka.pelisplus.domain.generic.Command;

public class ConfigCine extends Command {

    private String idCine;
    private String nombre;

    public ConfigCine(){

    }

    public void setIdCine(String idCine) {
        this.idCine = idCine;
    }

    public String getIdCine() {
        return idCine;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
