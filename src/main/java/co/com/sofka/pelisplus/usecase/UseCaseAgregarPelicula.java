package co.com.sofka.pelisplus.usecase;

import co.com.sofka.pelisplus.domain.cine.Cine;
import co.com.sofka.pelisplus.domain.cine.command.AgregarPelicula;
import co.com.sofka.pelisplus.domain.generic.DomainEvent;
import co.com.sofka.pelisplus.domain.generic.EventStoreRepository;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class UseCaseAgregarPelicula implements Function<AgregarPelicula, List<DomainEvent>> {

    private final EventStoreRepository repository;

    public UseCaseAgregarPelicula(EventStoreRepository repository){
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AgregarPelicula command) {
        List<DomainEvent> events =  repository.getEventsBy("cine", command.getIdCine());
        Cine cine = Cine.from(command.getIdCine(), events);
        cine.agregarPelicula(
                command.getIdCine(),
                command.getTitulo(),
                command.getGeneros(),
                command.getAnnio(),
                command.getSinopsis(),
                command.getUrl());
        return cine.getUncommittedChanges();
    }
}
