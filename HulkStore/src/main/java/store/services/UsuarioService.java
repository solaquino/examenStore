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

import store.domain.Usuario;
import store.methods.LOCK;


@Path("/usuarios")
@Consumes(value= MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class UsuarioService {
	
	//User database pre-initialization
	public static final List<Usuario> usuarios = new ArrayList<>();
	
	static {
		usuarios.add(new Usuario(1L, "oscar", "1234", "1"));
		usuarios.add(new Usuario(2L, "juan", "1234", "2"));
		usuarios.add(new Usuario(3L, "maria", "1234", "2"));
	}
	
	
	@GET
	public Response findAllUsers() {
		return Response.ok(this.usuarios).build();
	}
	
	@POST
	public Response createUser(Usuario usuarioRequest) {
		usuarioRequest.setId(usuarios.size()+1l);
		this.usuarios.add(usuarioRequest);
		return Response.ok(usuarioRequest).build();
	}
	
	@PUT
	public Response updateUser(Usuario usuarioRequest) {
		List<Usuario> found = this.usuarios.stream().filter(x -> usuarioRequest.getId() == x.getId()).collect(Collectors.toList());
		
		//Throws error in case of the usuario not found
		if(found.isEmpty()) return Response.status(Status.BAD_REQUEST).entity("Usuario no encontrado").build();
		
		Usuario updateUser = found.get(0);
		updateUser.setPassword(usuarioRequest.getPassword());
		updateUser.setUsername(usuarioRequest.getUsername());
		return Response.ok(updateUser).build();
	}
	
	@DELETE
	@Path("{usuarioId}")
	public Response deleteUser( @PathParam("usuarioId") long usuarioId) {
		System.out.println("usuarioId ==> " + usuarioId);
		List<Usuario> found = this.usuarios.stream().filter(x -> usuarioId == x.getId().longValue()).collect(Collectors.toList());
		
		//Throws error in case of the usuario not found
		if(found.isEmpty()) return Response.status(Status.BAD_REQUEST).entity("Usuario no encontrado").build();
		
		Usuario updateUser = found.get(0);
		this.usuarios.remove(updateUser);
		return Response.noContent().build();
	}
	
	
	@HEAD
	public Response pingUsersService() {
		return Response.noContent().header("corriendo", true).build();
	}
	
	@LOCK
	@Path("{usuarioId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response lockUser() {
		return Response.ok("Usuario bloqueado").build();
	}
	
}
