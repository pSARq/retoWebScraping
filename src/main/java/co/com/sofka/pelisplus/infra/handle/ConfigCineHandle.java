package co.com.sofka.pelisplus.infra.handle;

import co.com.sofka.pelisplus.domain.cine.command.ConfigCine;
import co.com.sofka.pelisplus.domain.generic.DomainEvent;
import co.com.sofka.pelisplus.infra.generic.UseCaseHandle;
import co.com.sofka.pelisplus.usecase.UseCaseConfigCine;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ConfigCineHandle extends UseCaseHandle {

    private final UseCaseConfigCine useCase;

    public ConfigCineHandle(UseCaseConfigCine useCase){
        this.useCase = useCase;
    }

    @ConsumeEvent(value = "sofka.cine.crear")
    void consumeBlocking(ConfigCine command) {
        List<DomainEvent> events = useCase.apply(command);
        process(command.getIdCine(), events);
    }
}
