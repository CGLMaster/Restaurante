package Integración.Proveedor;

import Negocio.Proveedor.TProveedor;

import java.util.List;

public interface DAOProveedor {

	public int altaProveedor(String nombre);

	public TProveedor buscarProveedor(int id);
	
	public TProveedor buscarProveedorPorNombre(String nombre);

	public List<TProveedor> buscarTodosProveedor();

	public int modificarProveedor(TProveedor proveedor);

	public int eliminarProveedor(int id);
	
	public int reactivar(int id_proveedor);

}