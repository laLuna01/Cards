package org.example;

import org.example.entities.Card;
import org.example.entities.Set;
import org.example.infrastructure.DatabaseConfig;
import org.example.repositories.CardRepository;
import org.example.repositories.SetRepository;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in org.example package
        final ResourceConfig rc = new ResourceConfig().packages("org.example.resources");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
//        server.stop();

        Set meuSet = new Set(5, "Além de Bandlewood ", 126, "v2.14", "25/08/21");
        Set meuSetAtualizado = new Set(5, "Bandle", 354, "v1.12", "11/03/2019");
        Card minhaCard = new Card(1, "Caitlyn", "Piltover e Zaun", "Campeão", "Campeão", 3, 3, 3, "Ataque rápido", "Golpear: Plante 2 Bombas de Clarão entre as 8 cartas no topo do deck inimigo.", "5+ das suas armadilhas foram ativadas.", "Caitlyn sempre foi uma investigadora obstinada e meticulosa, qualidades que a ajudaram a subir rapidamente nos ranques da polícia de Piltover. Mas, quando o caso de um certo C caiu em suas mãos, depois de ter sido abandonado por vários outros oficiais, parecia que Caitlyn finalmente havia encontrado alguém à sua altura. Quase.", meuSet, "https://cdn.cardsrealm.com/images/cartas/beyond-the-bandlewood/PT/med/caitlyn-05pz006.png?2654?&width=250");
        Card minhaCardAtualizada = new Card(1, "Vi", "Zaun", "campeao", "raro", 7, 2, 5, "resistencia", "ataca com luvas de ferro", "aumenta o ataque em 2", "cupcake", meuSetAtualizado, "linkImagemExemplo");
        System.out.println(meuSet);
        System.out.println(minhaCard);

        SetRepository setRepository = new SetRepository();
        CardRepository cardRepository = new CardRepository();

//        setRepository.Initialize();
//        cardRepository.Initialize();
//
//        setRepository.Create(meuSet);
//        cardRepository.Create(minhaCard);

//        System.out.println(cardRepository.Read(1));
//        System.out.println(setRepository.Read(5));

//        System.out.println(cardRepository.ReadAll());
//        System.out.println(setRepository.ReadAll());

//        setRepository.Update(meuSetAtualizado);
//        cardRepository.Update(minhaCardAtualizada);

//        setRepository.Delete(5);
//        cardRepository.Delete(1);
    }
}

