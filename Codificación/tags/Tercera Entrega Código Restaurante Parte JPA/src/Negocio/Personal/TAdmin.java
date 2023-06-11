package Negocio.Personal;

public class TAdmin extends TPersonal {
	
	int UUID=69;
	
	public TAdmin() {							//Transfer de Adminisrador del sistema para poder acceder a la gestión del Restaurante cuando no hay trabajadores activos
		super(-69, "Admin", "69696969Z", 69, 69.69, true, -69);
	}
	
}