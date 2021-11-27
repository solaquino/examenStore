package store.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import store.domain.Carrito;
import store.domain.Empleado;
import store.domain.Producto;
import store.domain.Tarjeta;
import store.methods.LOCK;


@Path("/carritos")
@Consumes(value= MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class CarritoService {
	
	//Carrito database pre-initialization
	public static final List<Carrito> carritos = new ArrayList<>();
	
	static {
		//Long id, Empleado empleado, List<Producto> productos, boolean status, String totalPago, Tarjeta tarjeta, Integer formaPago
		carritos.add(new Carrito(1L, EmpleadoService.empleados.get(1),null, true, "100.00", TarjetaService.tarjetas.get(0), 2));
		carritos.add(new Carrito(2L, EmpleadoService.empleados.get(1),null, true, "120.00", TarjetaService.tarjetas.get(0), 2));
		carritos.add(new Carrito(3L, EmpleadoService.empleados.get(2),null, true, "83.00", TarjetaService.tarjetas.get(2), 2));
	}
	
	
	@GET
	public Response findAllCarritos() {
		return Response.ok(this.carritos).build();
	}
	
	@POST
	public Response createCarrito(Carrito carritoRequest) {
		carritoRequest.setId(carritos.size()+1l);
		this.carritos.add(carritoRequest);
		return Response.ok(carritoRequest).build();
	}
	
	@PUT
	public Response updateCarrito(Carrito carritoRequest) {
		List<Carrito> found = this.carritos.stream().filter(x -> carritoRequest.getId() == x.getId()).collect(Collectors.toList());
		
		//Throws error in case of the carrito not found
		if(found.isEmpty()) return Response.status(Status.BAD_REQUEST).entity("Carrito no encontrado").build();
		
		Carrito updateCarrito = found.get(0);
		updateCarrito.setEmpleado(carritoRequest.getEmpleado());
		updateCarrito.setProductos(carritoRequest.getProductos());
		updateCarrito.setStatus(carritoRequest.isStatus());
		updateCarrito.setTotalPago(carritoRequest.getTotalPago());
		updateCarrito.setTarjeta(carritoRequest.getTarjeta());
		updateCarrito.setFormaPago(carritoRequest.getFormaPago());
		return Response.ok(updateCarrito).build();
	}
	
	@DELETE
	@Path("{carritoId}")
	public Response deleteCarrito( @PathParam("carritoId") long carritoId) {
		System.out.println("carritoId ==> " + carritoId);
		List<Carrito> found = this.carritos.stream().filter(x -> carritoId == x.getId().longValue()).collect(Collectors.toList());
		
		//Throws error in case of the carrito not found
		if(found.isEmpty()) return Response.status(Status.BAD_REQUEST).entity("Carrito no encontrado").build();
		
		Carrito updateCarrito = found.get(0);
		this.carritos.remove(updateCarrito);
		return Response.noContent().build();
	}
	
	
	@HEAD
	public Response pingCarritosService() {
		return Response.noContent().header("corriendo", true).build();
	}
	
	@LOCK
	@Path("{carritoId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response lockCarrito() {
		return Response.ok("Carrito bloqueado").build();
	}
	
}
