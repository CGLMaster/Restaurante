package Negocio.Producto;

public class TProdNoPerecedero extends TProducto {

	private String tipo;
	
	public TProdNoPerecedero(String nombre, int stock, float precio, int ID_Marca, int ID_Seccion, boolean activo, String tipo){
		super(nombre, stock, precio, ID_Marca, ID_Seccion, activo);
		this.tipo=tipo;
	}
	
	public TProdNoPerecedero(int ID_producto, String nombre, int stock, float precio, int ID_Marca, int ID_Seccion, boolean activo, String tipo){
		super(ID_producto, nombre, stock, precio, ID_Marca, ID_Seccion, activo);
		this.tipo=tipo;
	}

	public String getTipo(){
		return tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo=tipo;
	}
}