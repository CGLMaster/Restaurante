package Negocio.Ingrediente;

public class TIngrediente {

	private int id;
	private String nombre;
	private Boolean activo;
	private double calorias;

	public TIngrediente() {}

	public TIngrediente(String nombre, double calorias) {
		this.nombre = nombre;
		this.calorias = calorias;
	}
	
	public TIngrediente(int id, String nombre, double calorias, Boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
		this.calorias = calorias;
	}
	
	public TIngrediente(String nombre, double calorias, Boolean activo) {
		this.nombre = nombre;
		this.activo = activo;
		this.calorias = calorias;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCalorias() {
		return calorias;
	}

	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}

	public Boolean isActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}