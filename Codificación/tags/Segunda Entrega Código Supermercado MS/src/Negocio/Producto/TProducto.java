package Negocio.Producto;

public abstract class TProducto {
	
	private float precio;

	private int stock;

	private int ID_producto;

	private String nombre;
	
	private int ID_Marca;
	
	private int ID_Seccion;
	
	private boolean activo;

	public TProducto(String nombre, int stock, float precio, int ID_Marca, int ID_Seccion, boolean activo){
		this.nombre=nombre;
		this.stock=stock;
		this.precio=precio;
		this.ID_Marca=ID_Marca;
		this.ID_Seccion=ID_Seccion;
		this.activo=activo;
	}
	
	public TProducto(int ID_producto, String nombre, int stock, float precio, int ID_Marca, int ID_Seccion, boolean activo){
		this.ID_producto=ID_producto;
		this.nombre=nombre;
		this.stock=stock;
		this.precio=precio;
		this.ID_Marca=ID_Marca;
		this.ID_Seccion=ID_Seccion;
		this.activo=activo;
	}
	
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio=precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock=stock;
	}
	
	public void setIDProducto(int ID_producto) {
		this.ID_producto=ID_producto;
	}
	
	public int getIDProducto() {
		return ID_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public void setIDMarca(int ID_Marca) {
		this.ID_Marca=ID_Marca;
	}
	
	public int getIDMarca() {
		return ID_Marca;
	}
	
	public void setIDSeccion(int ID_Seccion) {
		this.ID_Seccion=ID_Seccion;
	}
	
	public int getIDSeccion() {
		return ID_Seccion;
	}
	
	public void setActivo(boolean activo) {
		this.activo=activo;
	}
	
	public boolean getActivo() {
		return activo;
	}
}