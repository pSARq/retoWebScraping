package co.com.sofka.pelisplus.infra.materialize;

import co.com.sofka.pelisplus.domain.cine.event.CineCreado;
import co.com.sofka.pelisplus.domain.cine.event.PeliculaAgregada;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
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

    @ConsumeEvent(value = "sofka.cine.peliculaagregada", blocking = true)
    void consumePeliculaAgregada(PeliculaAgregada event) {
        BasicDBObject document = new BasicDBObject();
        var key = "peliculas."+event.getTitulo();
        document.put(key+".titulo", event.getTitulo());
        document.put(key +".generos", event.getGeneros());
        document.put(key +".annio", event.getAnnio());
        document.put(key +".sinopsis", event.getSinopsis());
        document.put(key +".url", event.getUrl());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);

        mongoClient.getDatabase("queries")
                .getCollection("cine")
                .updateOne( Filters.eq("_id", event.getAggregateId()), updateObject);
    }

}