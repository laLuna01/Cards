package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.entities.Card;
import org.example.repositories.CardRepository;

import java.util.List;

@Path("/cards")
public class CardResource {
    private CardRepository cardRepository = new CardRepository();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Card card) {
        cardRepository.Create(card);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Card read(@PathParam("id") int id) {
        return cardRepository.Read(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Card> readAll() {
        return cardRepository.ReadAll();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Card card) {
        cardRepository.Update(card);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id) {
        cardRepository.Delete(id);
    }
}
