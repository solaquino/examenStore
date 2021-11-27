package store.domain;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

	private Long id;
	private Empleado empleado;
	private List<Producto> productos = new ArrayList<>();
	private boolean status; // true = llenandoCarrito, false = comprado
	private String totalPago; // monto, cifrado
	private Tarjeta tarjeta;
	private Integer formaPago; // 1 = efectivo, 2 = tarjeta

	public Carrito() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Carrito(Long id, Empleado empleado, List<Producto> productos, boolean status, String totalPago,
			Tarjeta tarjeta, Integer formaPago) {
		super();
		this.id = id;
		this.empleado = empleado;
		this.productos = productos;
		this.status = status;
		this.totalPago = totalPago;
		this.tarjeta = tarjeta;
		this.formaPago = formaPago;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(String totalPago) {
		this.totalPago = totalPago;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public Integer getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(Integer formaPago) {
		this.formaPago = formaPago;
	}

}
