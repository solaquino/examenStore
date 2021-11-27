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

import store.domain.Producto;
import store.methods.LOCK;


@Path("/productos")
@Consumes(value= MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class ProductoService {
	
	//Producto database pre-initialization
	public static final List<Producto> productos = new ArrayList<>();
	
	static {
		//Long id, String nombre, String descripcion, String tipo, String costo, String precioVenta, Integer stock, String basadoEn
		productos.add(new Producto(1L, "Vaso Hulk", "Vaso térmico serigrafiado con puños de Hulk", "vaso", "15.00", "20.00", 9, "Marvel"));
		productos.add(new Producto(2L, "Llavero Batman", "Llavero redondo con logo de Batman", "accesorio", "5.00", "11.00", 13, "DC Comics"));
		productos.add(new Producto(3L, "Playera gato feliz", "Playera de dama con imagen estampada de bigotes y sonrisa de gato", "camiseta", "115.00", "120.00", 39, "Alternativo"));
	}
	
	
	@GET
	public Response findAllProductos() {
		return Response.ok(this.productos).build();
	}
	
	@POST
	public Response createProducto(Producto productoRequest) {
		productoRequest.setId(productos.size()+1l);
		this.productos.add(productoRequest);
		return Response.ok(productoRequest).build();
	}
	
	@PUT
	public Response updateProducto(Producto productoRequest) {
		List<Producto> found = this.productos.stream().filter(x -> productoRequest.getId() == x.getId()).collect(Collectors.toList());
		
		//Throws error in case of the producto not found
		if(found.isEmpty()) return Response.status(Status.BAD_REQUEST).entity("Producto no encontrado").build();
		
		Producto updateProducto = found.get(0);
		updateProducto.setNombre(productoRequest.getNombre());
		updateProducto.setDescripcion(productoRequest.getDescripcion());
		updateProducto.setTipo(productoRequest.getTipo());
		updateProducto.setCosto(productoRequest.getCosto());
		updateProducto.setPrecioVenta(productoRequest.getPrecioVenta());
		updateProducto.setStock(productoRequest.getStock());
		updateProducto.setBasadoEn(productoRequest.getBasadoEn());
		return Response.ok(updateProducto).build();
	}
	
	@DELETE
	@Path("{productoId}")
	public Response deleteProducto( @PathParam("productoId") long productoId) {
		System.out.println("productoId ==> " + productoId);
		List<Producto> found = this.productos.stream().filter(x -> productoId == x.getId().longValue()).collect(Collectors.toList());
		
		//Throws error in case of the producto not found
		if(found.isEmpty()) return Response.status(Status.BAD_REQUEST).entity("Producto no encontrado").build();
		
		Producto updateProducto = found.get(0);
		this.productos.remove(updateProducto);
		return Response.noContent().build();
	}
	
	
	@HEAD
	public Response pingProductosService() {
		return Response.noContent().header("corriendo", true).build();
	}
	
	@LOCK
	@Path("{productoId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response lockProducto() {
		return Response.ok("Producto bloqueado").build();
	}
	
}
