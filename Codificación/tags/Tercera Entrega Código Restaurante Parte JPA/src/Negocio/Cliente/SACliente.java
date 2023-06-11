package Negocio.Cliente;

import java.util.List;

public interface SACliente {

	public int altaCliente(TCliente cliente);

	public int bajaCliente(int id_trabajador);

	public int modificarCliente(TCliente cliente);

	public TCliente buscarUnoCliente(int id);

	public List<TCliente> buscarTodosCliente();

	public int buscarUnoClientePorDni(String dni);
}