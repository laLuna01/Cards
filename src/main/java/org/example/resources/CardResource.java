package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.Card;
import org.example.repositories.CardRepository;

import java.util.List;

@Path("/cards")
public class CardResource {
    private CardRepository cardRepository = new CardRepository();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Card card) {
        cardRepository.Create(card);
        return Response.status(Response.Status.CREATED).entity(card).build();
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
    public Response update(Card card) {
        cardRepository.Update(card);
        return Response.status(Response.Status.OK).entity(card).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        cardRepository.Delete(id);
        return Response.status(Response.Status.OK).build();
    }
}
