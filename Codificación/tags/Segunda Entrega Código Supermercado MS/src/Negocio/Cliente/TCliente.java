package Negocio.Cliente;


public abstract class TCliente {
	
	private String nombre;
	
	private String DNI;
	
	private String mail;
	
	private int ID_socio;
	
	private boolean esActivo;
	
	public TCliente(String nombre, String DNI, String mail, int ID_socio, boolean esActivo) {
		this.nombre = nombre;
		this.DNI = DNI;
		this.mail = mail;
		this.ID_socio = ID_socio;
		this.esActivo = esActivo;
	}
	
	public TCliente() {
		
	}
	
	public TCliente(int ID_socio) {
		this.ID_socio = ID_socio;
	}

	public String getNombre() {
		return nombre;
	}

	
	public String getDNI() {
		return DNI;
	}

	
	public String getMail() {
		return mail;
	}

	
	public int getIDSocio() {
		return ID_socio;
	}
	
	public boolean getEsActivo(){
		return esActivo;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void setEsActivo(boolean activo) {
		this.esActivo = activo;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}

	public void setID_socio(int iD_socio) {
		ID_socio = iD_socio;
	}
	
	
	
}