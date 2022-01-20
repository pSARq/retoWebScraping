package co.com.sofka.pelisplus.infra.materialize;

import co.com.sofka.pelisplus.domain.cine.event.CineCreado;
import com.mongodb.client.MongoClient;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class CineHandle {
    private final MongoClient mongoClient;

    public CineHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @ConsumeEvent(value = "sofka.cine.cinecreado", blocking = true)
    void consumeCineCreado(CineCreado event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("nombre", event.getNombre());

        mongoClient.getDatabase("queries")
                .getCollection("cine")
                .insertOne(new Document(document));
    }

}