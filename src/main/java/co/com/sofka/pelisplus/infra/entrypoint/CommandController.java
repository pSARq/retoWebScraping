package co.com.sofka.pelisplus.infra.entrypoint;

import co.com.sofka.pelisplus.domain.cine.command.AgregarPelicula;
import co.com.sofka.pelisplus.domain.cine.command.ConfigCine;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class CommandController {

    private final EventBus bus;

    public CommandController(EventBus bus){
        this.bus = bus;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/crear")
    public Response executor(ConfigCine command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/extraerPeliculas")
    public Response executor(AgregarPelicula command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }
}
