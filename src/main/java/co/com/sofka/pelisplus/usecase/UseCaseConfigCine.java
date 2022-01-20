package co.com.sofka.pelisplus.usecase;

import co.com.sofka.pelisplus.domain.cine.Cine;
import co.com.sofka.pelisplus.domain.cine.command.ConfigCine;
import co.com.sofka.pelisplus.domain.generic.DomainEvent;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class UseCaseConfigCine implements Function<ConfigCine, List<DomainEvent>> {

    @Override
    public List<DomainEvent> apply(ConfigCine command) {
        Cine cine = new Cine(command.getIdCine(), command.getNombre());
        return cine.getUncommittedChanges();
    }
}
