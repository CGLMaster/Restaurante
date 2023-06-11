package Integración.Cliente;


import java.util.List;

import Negocio.Cliente.TCliente;


public interface DAOCliente {
	
	public int altaCliente(TCliente cliente);
	
	public int bajaCliente(int id);

	public int modificarCliente(TCliente cliente);

	public TCliente buscarUnCliente(int id);

	public List<TCliente> buscarTodosCliente();

	public int reactivar(int id_cliente);

	public int buscarUnClientePorDni(String dni);
}