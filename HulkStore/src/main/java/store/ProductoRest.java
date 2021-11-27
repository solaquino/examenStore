package store;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import store.domain.Producto;

@Path("/producto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)	
public class ProductoRest {
	
	@POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregar(Producto producto) { 
        return Response.status(201).entity(producto).build(); 
    }
	
	@PUT
    @Path("/put")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Producto producto) { 
        return Response.status(201).entity(producto).build(); 
    }
	
	@GET
    @Path("/get")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consultar() { 
		return Response.ok("Ver productos ok", MediaType.APPLICATION_JSON).build(); 
    }
	
}