package Negocio.Compra;

public class TLineaDeCompra {

	private int IDProducto;
	private int ID_Compra;
	private double precio;
	private int cantidad;
	
	public TLineaDeCompra() {}
	
	public TLineaDeCompra(int iDProducto, int iD_Compra, double precio, int cantidad) {
		super();
		IDProducto = iDProducto;
		ID_Compra = iD_Compra;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	
	public TLineaDeCompra(int iDProducto, int cantidad, int precio) {
		IDProducto = iDProducto;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public TLineaDeCompra(int iDProducto, int cantidad) {
		super();
		IDProducto = iDProducto;
		this.cantidad = cantidad;
	}

	public int getIDProducto() {
		return IDProducto;
	}

	public void setIDProducto(int iDProducto) {
		IDProducto = iDProducto;
	}

	public int getID_Compra() {
		return ID_Compra;
	}

	public void setID_Compra(int iD_Compra) {
		ID_Compra = iD_Compra;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}