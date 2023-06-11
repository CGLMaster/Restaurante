package Negocio.Turno;

public class TTurno {
	
	private String nombre;

	private String hora_inicio;

	private String hora_fin;

	private Boolean activo;
	
	private int ID;
	
	public TTurno(String nombre){
		this.nombre = nombre;
		this.activo = true;
	}
	
	public TTurno(int id, String nombre){
		this.ID = id;
		this.nombre = nombre;
	}
	
	public TTurno(int id, String nombre, String hIni, String hFin, Boolean activo){
		this.ID = id;
		this.nombre = nombre;
		this.hora_inicio = hIni;
		this.hora_fin = hFin;
		this.activo = activo;
	}
	
	public TTurno(String nombre, String hIni, String hFin, Boolean activo){
		this.nombre = nombre;
		this.hora_inicio = hIni;
		this.hora_fin = hFin;
		this.activo = activo;
	}
	
	public int getID(){
		return ID;
	}
	
	public void setID(int id_turno){
		ID = id_turno;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getHoraInicio() {
		return hora_inicio;
	}

	public void setHoraInicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public String getHoraFin() {
		return hora_fin;
	}

	public void setHoraFin(String hora_fin) {
		this.hora_fin = hora_fin;
	}

	public Boolean isActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}