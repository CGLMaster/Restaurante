package Negocio.Trabajador;

import java.util.List;

public interface SATrabajador {

	public int altaTrabajador(TTrabajador trabajador);

	public int eliminarTrabajador(int ID);

	public int modificarDatosTrabajador(TTrabajador trabajador);

	public TTrabajador identificarTrabajador(int ID);

	public List<TTrabajador> buscarTodosTrabajadores();

	public TTrabajador buscarDatosTrabajadorPorID(int ID);

	public TTrabajador buscarDatosTrabajadorPorDNI(String DNI);
}