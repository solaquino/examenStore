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
import store.methods.LOCK;


@Path("/empleados")
@Consumes(value= MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class EmpleadoService {
	
	//Empleado database pre-initialization
	public static final List<Empleado> empleados = new ArrayList<>();
	
	static {
		empleados.add(new Empleado(1L, "Omar", "Torres", "Mendoza", UsuarioService.usuarios.get(0)));
		empleados.add(new Empleado(2L, "Ana", "Juarez", "Sosa", UsuarioService.usuarios.get(1)));
		empleados.add(new Empleado(3L, "Laura", "Aguilar", "Contreras", UsuarioService.usuarios.get(2)));
	}
	
	
	@GET
	public Response findAllEmpleados() {
		return Response.ok(this.empleados).build();
	}
	
	@POST
	public Response createEmpleado(Empleado empleadoRequest) {
		empleadoRequest.setId(empleados.size()+1l);
		this.empleados.add(empleadoRequest);
		return Response.ok(empleadoRequest).build();
	}
	
	@PUT
	public Response updateEmpleado(Empleado empleadoRequest) {
		List<Empleado> found = this.empleados.stream().filter(x -> empleadoRequest.getId() == x.getId()).collect(Collectors.toList());
		
		//Throws error in case of the empleado not found
		if(found.isEmpty()) return Response.status(Status.BAD_REQUEST).entity("Empleado no encontrado").build();
		
		Empleado updateEmpleado = found.get(0);
		updateEmpleado.setNombre(empleadoRequest.getNombre());
		updateEmpleado.setApellidoPaterno(empleadoRequest.getApellidoPaterno());
		updateEmpleado.setApellidoMaterno(empleadoRequest.getApellidoMaterno());
		updateEmpleado.setUser(empleadoRequest.getUser());
		return Response.ok(updateEmpleado).build();
	}
	
	@DELETE
	@Path("{empleadoId}")
	public Response deleteEmpleado( @PathParam("empleadoId") long empleadoId) {
		System.out.println("empleadoId ==> " + empleadoId);
		List<Empleado> found = this.empleados.stream().filter(x -> empleadoId == x.getId().longValue()).collect(Collectors.toList());
		
		//Throws error in case of the empleado not found
		if(found.isEmpty()) return Response.status(Status.BAD_REQUEST).entity("Empleado no encontrado").build();
		
		Empleado updateEmpleado = found.get(0);
		this.empleados.remove(updateEmpleado);
		return Response.noContent().build();
	}
	
	
	@HEAD
	public Response pingEmpleadosService() {
		return Response.noContent().header("corriendo", true).build();
	}
	
	@LOCK
	@Path("{empleadoId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response lockEmpleado() {
		return Response.ok("Empleado bloqueado").build();
	}
	
}
