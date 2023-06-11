package Integración.Trabajador;

import java.util.List;

import Negocio.Trabajador.TTrabajador;

public interface DAOTrabajador {

	public int altaTrabajador(TTrabajador trabajador);

	public int bajaTrabajador(int id);

	public int modificarTrabajador(TTrabajador trabajador);

	public List<TTrabajador> buscarTodosTrabajador();

	public TTrabajador buscarDatosTrabajadorID(int id);

	public TTrabajador buscarDatosTrabajadorDNI(String DNI);

	int reactivar(int id);
}