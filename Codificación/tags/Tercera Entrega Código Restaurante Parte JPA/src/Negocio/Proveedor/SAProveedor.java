package Negocio.Proveedor;

import java.util.List;

public interface SAProveedor {

	public int altaProveedor(String nombre);

	public TProveedor buscarProveedor(int id);

	public List<TProveedor> buscarTodosProveedor();

	public int modificarProveedor(TProveedor proveedor);

	public int eliminarProveedor(int id);
}