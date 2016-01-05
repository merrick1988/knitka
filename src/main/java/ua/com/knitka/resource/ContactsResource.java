package ua.com.knitka.resource;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.InputStream;

@Path("/contacts")
public interface ContactsResource {
    @GET
    @Produces("application/json")
    public void getContacts(final @Suspended AsyncResponse asyncResponse);
}
