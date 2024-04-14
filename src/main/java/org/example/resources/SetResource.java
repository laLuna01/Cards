package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.entities.Set;
import org.example.repositories.SetRepository;

import java.util.List;

@Path("/sets")
public class SetResource {
    private SetRepository setRepository = new SetRepository();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Set set) {
        setRepository.Create(set);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Set read(@PathParam("id") int id) {
        return setRepository.Read(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Set> readAll() {
        return setRepository.ReadAll();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Set set) {
        setRepository.Update(set);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id) {
        setRepository.Delete(id);
    }
}
