package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.Set;
import org.example.repositories.SetRepository;

import java.util.List;

@Path("/sets")
public class SetResource {
    private SetRepository setRepository = new SetRepository();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Set set) {
        setRepository.Create(set);
        return Response.status(Response.Status.CREATED).entity(set).build();
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
    public Response update(Set set) {
        setRepository.Update(set);
        return Response.status(Response.Status.OK).entity(set).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        setRepository.Delete(id);
        return Response.status(Response.Status.OK).build();
    }
}
