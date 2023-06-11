package Negocio.Pedido;

import java.time.LocalDate;

public class TPedido {

	private int ID;

	private Double PrecioTotal;
	
	private Double PrecioPagado;
	
	private LocalDate Fecha;

	private int Id_cliente;

	private int Id_personal;
	
	public TPedido(){
		PrecioTotal=0.0;
	}
	
	public TPedido(int Id_cliente, int Id_personal, Double PrecioTotal){
		this.Id_cliente=Id_cliente;
		this.Id_personal=Id_personal;
		this.PrecioTotal=PrecioTotal;
	}
	
	public TPedido(int ID, int Id_cliente, int Id_personal, Double PrecioTotal, LocalDate fecha){
		this.ID = ID;
		this.Id_personal = Id_personal;
		this.Id_cliente = Id_cliente;
		this.PrecioTotal = PrecioTotal;
		Fecha = fecha;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		this.ID=id;
	}

	public LocalDate getFecha() {
		return Fecha;
	}

	public void setFecha(LocalDate fecha) {
		Fecha = fecha;
	}

	public int getId_cliente() {
		return Id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		Id_cliente = id_cliente;
	}

	public int getId_personal() {
		return Id_personal;
	}

	public void setId_personal(int id_personal) {
		this.Id_personal = id_personal;
	}

	public Double getPrecioTotal() {
		return PrecioTotal;
	}

	public void setPrecioTotal(Double precio) {
		PrecioTotal = precio;
	}

	public void setPrecioPagado(Double i) {
		PrecioPagado=i;
		
	}

	public double getPrecioPagado() {
		return PrecioPagado;
	}

}