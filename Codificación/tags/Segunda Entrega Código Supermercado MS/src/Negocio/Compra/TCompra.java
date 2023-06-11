package Negocio.Compra;


import java.time.LocalDate;


public class TCompra {

	private int ID_compra;
	
	private float precio_total;
	
	private LocalDate fecha;

	private int ID_Cliente;

	private int ID_Trabajador;
	
	private boolean activo;
	
	private float precio_pagado;
	
	public TCompra(){
		
	}
	
	public TCompra(int ID_compra, LocalDate fecha, int ID_Cliente, int ID_Trabajador, float precioTotal){
		this.fecha = fecha;
		this.ID_Cliente = ID_Cliente;
		this.ID_Trabajador = ID_Trabajador;
		this.precio_total = precioTotal;
		this.ID_compra = ID_compra;
	}
	
	public int getIDCompra() {
		
		return ID_compra;
		
	}

	public void setID_Compra(int iD_Compra) {
		ID_compra = iD_Compra;
	}
	
	public float getPrecioTotal() {
		
		return precio_total;
		
	}

	public void setPrecioTotal(float precioTotal) {
		precio_total = precioTotal;
		
	}
	public LocalDate getFecha() {
	
		return fecha;
		
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
	
	public int getIdTrabajador() {
		return ID_Trabajador;
	}

	public void setIdTrabajador(int iD_Trabajador) {
		ID_Trabajador = iD_Trabajador;
	}
	
	public int getIdCliente() {
		return ID_Cliente;
	}

	public void setIdCliente(int iD_Cliente) {
		ID_Cliente = iD_Cliente;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public void setPrecioPagado(float precioPagado){
		precio_pagado=precioPagado;
	}
	public float getPrecioPagado(){
		return precio_pagado;
	}
}