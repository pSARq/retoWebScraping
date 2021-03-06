package co.com.sofka.pelisplus.infra.entrypoint;

import com.mongodb.client.MongoClient;
import org.bson.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/api")
public class QueryController {
    private final MongoClient mongoClient;

    public QueryController(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cine/{id}")
    public Response get(@PathParam("id") String idCine) {
        List<Document> documentList = new ArrayList<>();
        mongoClient.getDatabase("queries")
                .getCollection("cine")
                .find()
                //.find(Filters.eq("_id", idCine))
                .forEach(documentList::add);
        return Response.ok(documentList).build();
    }
}