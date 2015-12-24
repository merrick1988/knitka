package ua.com.knitka.resource;

import ua.com.knitka.model.CustomerDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.InputStream;
import java.io.ObjectInputStream;

@Path("/customers")
public interface CustomerResource {
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    public Response createCustomer(InputStream is);

    @GET
    @Path("{id}")
    @Produces("application/xml")
    public StreamingOutput getCustomer(@PathParam("id") int id);

    @PUT
    @Path("{id}")
    @Consumes("application/xml")
    public void updateCustomer(@PathParam("id") int id, InputStream is);
}
