package Negocio.Producto;

import java.time.LocalDate;

public class TProdPerecedero extends TProducto {
	
	private LocalDate fecha_de_caducidad;
	
	public TProdPerecedero(String nombre, int stock, float precio, int ID_Marca, int ID_Seccion, boolean activo, LocalDate fecha_de_caducidad){
		super(nombre, stock, precio, ID_Marca, ID_Seccion, activo);
		this.fecha_de_caducidad=fecha_de_caducidad;
	}
	
	public TProdPerecedero(int ID_producto, String nombre, int stock, float precio, int ID_Marca, int ID_Seccion, boolean activo, LocalDate fecha_de_caducidad){
		super(ID_producto, nombre, stock, precio, ID_Marca, ID_Seccion, activo);
		this.fecha_de_caducidad=fecha_de_caducidad;
	}
	
	public LocalDate getFechaDeCaducidad(){
		return fecha_de_caducidad;
	}
	
	public void setFechaDeCaducidad(LocalDate fecha_de_caducidad){
		this.fecha_de_caducidad=fecha_de_caducidad;
	}
}