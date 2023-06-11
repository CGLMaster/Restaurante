package Negocio.Personal;

public abstract class TPersonal {

	private int id_personal;

	private String nombre;

	private String dni;

	private int numPedidos;

	private Double sueldo;

	private boolean activo;

	private int id_turno;

	public TPersonal(int id, String nombre, String dni, int numPedidos, Double sueldo, Boolean activo, int idTurno) {
		this.id_personal = id;
		this.nombre = nombre;
		this.dni = dni;
		this.numPedidos = numPedidos;
		this.sueldo = sueldo;
		this.activo = activo;
		this.id_turno = idTurno;
	}

	public TPersonal(String nombre, String dni, int numPedidos, Double sueldo, Boolean activo, int idTurno) {
		this.nombre = nombre;
		this.dni = dni;
		this.numPedidos = numPedidos;
		this.sueldo = sueldo;
		this.activo = activo;
		this.id_turno = idTurno;
	}

	public TPersonal() {
	}

	public int getIDPersonal() {
		return id_personal;
	}

	public int getIDTurno() {
		return id_turno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getNumPedidos() {
		return numPedidos;
	}

	public void setNumPedidos(int numPedidos) {
		this.numPedidos = numPedidos;
	}

	public Double getSueldo() {
		return sueldo;
	}

	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}

	public void setId_turno(int id_turno) {
		this.id_turno = id_turno;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setID(int id) {
		this.id_personal = id;
	}

	public int getID() {
		return id_personal;
	}
}