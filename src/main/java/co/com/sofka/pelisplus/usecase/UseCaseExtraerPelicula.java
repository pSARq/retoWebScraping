package co.com.sofka.pelisplus.usecase;

import co.com.sofka.pelisplus.domain.cine.Cine;
import co.com.sofka.pelisplus.domain.cine.command.AgregarPelicula;
import co.com.sofka.pelisplus.domain.generic.DomainEvent;
import co.com.sofka.pelisplus.domain.generic.EventStoreRepository;
import co.com.sofka.pelisplus.infra.model.DataResponse;
import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

@Dependent
public class UseCaseExtraerPelicula implements Function<AgregarPelicula, List<DomainEvent>> {
    private static final String URL_BASE = "https://pelisplus.so";
    private final EventStoreRepository repository;

    public UseCaseExtraerPelicula(EventStoreRepository repository){
        this.repository = repository;
    }

    private Connection.Response getResponse(String path) throws IOException {
        return Jsoup.connect(URL_BASE)
                        .userAgent("Mozilla/5.0")
                        .timeout(10 * 1000)
                        .method(Connection.Method.POST)
                        .followRedirects(true)
                        .execute();
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }

    @Override
    public List<DomainEvent> apply(AgregarPelicula command) {
        Cine cine = Cine.from(command.getIdCine(),
                repository.getEventsBy("pelisplus", command.getIdCine())
        );

        try {
            Connection.Response response = getResponse(command.getUrl());
            new Gson().fromJson(response.body(), DataResponse.class).getData().stream()
                    .filter(data -> data.get(5).contains(""))
                    .forEach(data -> cine.agregarPelicula(command.getIdCine(), command.getTitulo(), command.getGeneros(), command.getAnnio(), command.getSinopsis(), command.getUrl()));
            return cine.getUncommittedChanges();
        } catch (IOException e) {
            throw new ExceptionExtrataerPelicula();
        }
    }
}