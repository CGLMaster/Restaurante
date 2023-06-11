package Negocio.ClienteRest;


public class TClienteRest {
	
	private int id;
	
	private String nombre;
	
	private String apellidos;
	
	private String dni;
	
	private boolean activo;

	
	
	public TClienteRest(int id, String nombre, String apellidos, String dni,boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.activo = activo;
	}


	public TClienteRest(String nombre, String apellidos, String dni, boolean activo) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.activo = activo;
	}


	public TClienteRest() {
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

	
	public String getApellidos() {
		return apellidos;
	}

	
	public void setApellidos(String ape) {
		this.apellidos = ape;
	}

	
	public String getDni() {
		return dni;
	}

	
	public void setDni(String dni) {
		  this.dni= dni;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}