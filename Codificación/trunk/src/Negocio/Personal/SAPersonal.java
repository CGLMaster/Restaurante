package Negocio.Personal;

import java.util.List;


public interface SAPersonal {
	
	public int altaPersonal(TPersonal personal);

	
	public int bajaPersonal(int id);
	

	public int modificarPersonal(TPersonal personal);

	
	public TPersonal buscarUnPersonalPorID(int id);

	
	public TPersonal buscarUnPersonalPorDNI(String dni);

	
	public List<TPersonal> buscarTodosPersonal();


	public TPersonal identificarPersonal(int id);
}