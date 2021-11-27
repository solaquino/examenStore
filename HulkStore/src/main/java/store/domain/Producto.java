package store.domain;

public class Producto {

	private Long id;
	private String nombre;
	private String descripcion;
	private String tipo; //camiseta, vaso, comic, juguete, accesorio
	private String costo; //cuanto costó el producto, dato oculto
	private String precioVenta; //a cuanto se vende, dato oculto
	private Integer stock;// cuantos hay
	private String basadoEn; //Marvel, DC Comics, Alternativo

	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Producto(Long id, String nombre, String descripcion, String tipo, String costo, String precioVenta,
			Integer stock, String basadoEn) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.costo = costo;
		this.precioVenta = precioVenta;
		this.stock = stock;
		this.basadoEn = basadoEn;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCosto() {
		return costo;
	}
	public void setCosto(String costo) {
		this.costo = costo;
	}
	public String getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(String precioVenta) {
		this.precioVenta = precioVenta;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getBasadoEn() {
		return basadoEn;
	}
	public void setBasadoEn(String basadoEn) {
		this.basadoEn = basadoEn;
	}


}
