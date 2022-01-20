package co.com.sofka.pelisplus.infra.generic;

import co.com.sofka.pelisplus.domain.generic.DomainEvent;
import co.com.sofka.pelisplus.domain.generic.EventStoreRepository;
import co.com.sofka.pelisplus.infra.message.BusService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

//@ApplicationScoped
public abstract class UseCaseHandle {

    @Inject
    private  EventStoreRepository repository;

    @Inject
    private BusService busService;

    public void process(String idCine, List<DomainEvent> events) {
        events.stream().map(event -> {
            String eventBody = EventSerializer.instance().serialize(event);
            return new StoredEvent(event.getClass().getTypeName(), new Date(), eventBody);
        }).forEach(storedEvent -> repository.saveEvent("cine", idCine, storedEvent));

        events.forEach(busService::send);
    }
}