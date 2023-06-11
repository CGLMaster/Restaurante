package Negocio.Plato;

public class TBebida extends TPlato {

	private double volumen;

	public TBebida(int id, String nombre, String descripcion, double precio, int stock, boolean activo, double volumen) {
		super(id, nombre, descripcion, precio, stock, activo);
		this.volumen = volumen;
	}

	public TBebida(String nombre, String descripcion, double precio, int stock, boolean activo, double volumen) {
		super(nombre, descripcion, precio, stock, activo);
		this.volumen = volumen;
	}

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}
}