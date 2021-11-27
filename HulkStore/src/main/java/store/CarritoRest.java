package store;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import store.domain.Carrito;

@Path("/carrito")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)	
public class CarritoRest {
	
	@POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregar(Carrito carrito) { 
        return Response.status(201).entity(carrito).build(); 
    }
	
	@PUT
    @Path("/put")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Carrito carrito) { 
        return Response.status(201).entity(carrito).build(); 
    }
	
	@GET
    @Path("/get")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consultar() { 
		return Response.ok("Ver carritos ok", MediaType.APPLICATION_JSON).build(); 
    }
	
}