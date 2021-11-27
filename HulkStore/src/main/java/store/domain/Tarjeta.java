package store.domain;

public class Tarjeta {

	private Long id;
	private boolean status; //true = activa, false = inactiva
	private String numTarjeta; //formato ### XXXX XXXX #### 
	private String nombrePropietario;
	private String anioExpiracion;
	private String mesExpiracion;
	private String marca;//Mastercard, Visa, American Express
	private Empleado empleado;
	private String idPasarela;	//Se plantea que los datos sensibles se gestionen en una pasarela
	//por ejemplo Openpay
	public Tarjeta() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tarjeta(Long id, boolean status, String numTarjeta, String nombrePropietario,
			String anioExpiracion, String mesExpiracion, String marca, Empleado empleado,
			String idPasarela) {
		super();
		this.id = id;
		this.status = status;
		this.numTarjeta = numTarjeta;
		this.nombrePropietario = nombrePropietario;
		this.anioExpiracion = anioExpiracion;
		this.mesExpiracion = mesExpiracion;
		this.marca = marca;
		this.empleado = empleado;
		this.idPasarela = idPasarela;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getNumTarjeta() {
		return numTarjeta;
	}
	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}
	public String getNombrePropietario() {
		return nombrePropietario;
	}
	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}
	public String getAnioExpiracion() {
		return anioExpiracion;
	}
	public void setAnioExpiracion(String anioExpiracion) {
		this.anioExpiracion = anioExpiracion;
	}
	public String getMesExpiracion() {
		return mesExpiracion;
	}
	public void setMesExpiracion(String mesExpiracion) {
		this.mesExpiracion = mesExpiracion;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public String getIdPasarela() {
		return idPasarela;
	}
	public void setIdPasarela(String idPasarela) {
		this.idPasarela = idPasarela;
	}

}
