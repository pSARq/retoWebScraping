package co.com.sofka.pelisplus.usecase;

import co.com.sofka.pelisplus.domain.cine.Cine;
import co.com.sofka.pelisplus.domain.cine.command.AgregarPelicula;
import co.com.sofka.pelisplus.domain.generic.DomainEvent;
import co.com.sofka.pelisplus.domain.generic.EventStoreRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

@Dependent
public class UseCaseAgregarPelicula implements Function<AgregarPelicula, List<DomainEvent>> {

    private final EventStoreRepository repository;
    final String baseURL = "https://pelisplus.so/estrenos";

    public UseCaseAgregarPelicula(EventStoreRepository repository){
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AgregarPelicula command) {
        var cine = Cine.from(command.getIdCine(),
                repository.getEventsBy("cine", command.getIdCine()));

        var document = extraerDocumento();

        for (Element row : document.select(".items-peliculas .item-pelicula a")) {
            final String urlPelicula = row.attr("href");
            try {
                final Document movie = Jsoup.connect("https://pelisplus.so" + urlPelicula).get();

                String titulo = movie.select(".info-content h1").text();
                String generos = movie.select(".info-content p:nth-of-type(4) span:nth-of-type(2)").text();
                String sinopsis = movie.select(".sinopsis").text();
                String annio = movie.select(".info-content p:nth-of-type(2) span:nth-of-type(2)").text();
                String url = movie.select(".player.player-normal ul:nth-of-type(2)  li:nth-of-type(1)").attr("data-video");

                cine.agregarPelicula(command.getIdPelicula(), titulo, generos, annio, sinopsis, url);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new ExceptionExtrataerPelicula();
            }
        }
        return cine.getUncommittedChanges();
    }

    public Document extraerDocumento(){
        try {
            return Jsoup.connect(baseURL).get();
        } catch (IOException e) {
            throw new ExceptionExtrataerPelicula();
        }
    }
}
