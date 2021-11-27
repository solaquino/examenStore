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

import store.domain.Empleado;
import store.domain.Tarjeta;
import store.methods.LOCK;


@Path("/tarjetas")
@Consumes(value= MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class TarjetaService {
	
	//Tarjeta database pre-initialization
	public static final List<Tarjeta> tarjetas = new ArrayList<>();
	
	static {
		//(Long id, boolean status, String numTarjeta, String nombrePropietario, String anioExpiracion, String mesExpiracion, String marca, Empleado empleado, String idPasarela)
		tarjetas.add(new Tarjeta(1L, true, "4152 XXXX XXXX 1234", "Miguel Allende", "25", "04", "Visa", EmpleadoService.empleados.get(0), "a4036"));
		tarjetas.add(new Tarjeta(2L, true, "4152 XXXX XXXX 3349", "Juan Reyes", "23", "11", "Mastercard", EmpleadoService.empleados.get(0), "e1103"));
	}
	
	
	@GET
	public Response findAllTarjetas() {
		return Response.ok(this.tarjetas).build();
	}
	
	@POST
	public Response createTarjeta(Tarjeta tarjetaRequest) {
		tarjetaRequest.setId(tarjetas.size()+1l);
		this.tarjetas.add(tarjetaRequest);
		return Response.ok(tarjetaRequest).build();
	}
	
	@PUT
	public Response updateTarjeta(Tarjeta tarjetaRequest) {
		List<Tarjeta> found = this.tarjetas.stream().filter(x -> tarjetaRequest.getId() == x.getId()).collect(Collectors.toList());
		
		//Throws error in case of the tarjeta not found
		if(found.isEmpty()) return Response.status(Status.BAD_REQUEST).entity("Tarjeta no encontrado").build();
		
		Tarjeta updateTarjeta = found.get(0);
		updateTarjeta.setStatus(tarjetaRequest.isStatus());
		updateTarjeta.setNumTarjeta(tarjetaRequest.getNumTarjeta());
		updateTarjeta.setNombrePropietario(tarjetaRequest.getNombrePropietario());
		updateTarjeta.setAnioExpiracion(tarjetaRequest.getAnioExpiracion());
		updateTarjeta.setMesExpiracion(tarjetaRequest.getMesExpiracion());
		updateTarjeta.setMarca(tarjetaRequest.getMarca());
		updateTarjeta.setEmpleado(tarjetaRequest.getEmpleado());
		updateTarjeta.setIdPasarela(tarjetaRequest.getIdPasarela());
		return Response.ok(updateTarjeta).build();
	}
	
	@DELETE
	@Path("{tarjetaId}")
	public Response deleteTarjeta( @PathParam("tarjetaId") long tarjetaId) {
		System.out.println("tarjetaId ==> " + tarjetaId);
		List<Tarjeta> found = this.tarjetas.stream().filter(x -> tarjetaId == x.getId().longValue()).collect(Collectors.toList());
		
		//Throws error in case of the tarjeta not found
		if(found.isEmpty()) return Response.status(Status.BAD_REQUEST).entity("Tarjeta no encontrado").build();
		
		Tarjeta updateTarjeta = found.get(0);
		this.tarjetas.remove(updateTarjeta);
		return Response.noContent().build();
	}
	
	
	@HEAD
	public Response pingTarjetasService() {
		return Response.noContent().header("corriendo", true).build();
	}
	
	@LOCK
	@Path("{tarjetaId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response lockTarjeta() {
		return Response.ok("Tarjeta bloqueado").build();
	}
	
}
