package co.com.sofka.pelisplus.infra.handle;

import co.com.sofka.pelisplus.domain.cine.command.AgregarPelicula;
import co.com.sofka.pelisplus.domain.generic.DomainEvent;
import co.com.sofka.pelisplus.infra.generic.UseCaseHandle;
import co.com.sofka.pelisplus.usecase.UseCaseAgregarPelicula;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class AddPeliculaHandle extends UseCaseHandle {

    private final UseCaseAgregarPelicula useCase;

    public AddPeliculaHandle(UseCaseAgregarPelicula useCase){
        this.useCase = useCase;
    }

    @ConsumeEvent(value = "sofka.cine.addpelicula")
    void consumeBlocking(AgregarPelicula command) {
        List<DomainEvent> events = useCase.apply(command);
        process(command.getIdCine(), events);
    }
}
