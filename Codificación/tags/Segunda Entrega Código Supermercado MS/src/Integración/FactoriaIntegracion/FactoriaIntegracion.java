package Integraci�n.FactoriaIntegracion;

import Integraci�n.Marca.DAOMarca;
import Integraci�n.Seccion.DAOSeccion;
import Integraci�n.Trabajador.DAOTrabajador;
import Integraci�n.Producto.DAOProducto;
import Integraci�n.Cliente.DAOCliente;
import Integraci�n.Compra.DAOCompra;
import Integraci�n.Proveedor.DAOProveedor;
import Integraci�n.Producto.DAODistribuye;
import Integraci�n.Compra.DAOProductoCompra;


public abstract class FactoriaIntegracion {
	
	
	private static FactoriaIntegracion instancia;

	
	public static FactoriaIntegracion getInstance() {
		if(instancia == null) instancia = new FactoriaIntegracionImp();
		
		return instancia;
	}

	public abstract DAOMarca getDAOMarca();

	
	public abstract DAOSeccion getDAOSeccion();

	
	public abstract DAOTrabajador getDAOTrabajador();

	
	public abstract DAOProducto getDAOProducto();

	
	public abstract DAOCliente getDAOCliente();

	
	public abstract DAOCompra getDAOCompra();

	
	public abstract DAOProveedor getDAOProveedor();

	
	public abstract DAODistribuye getDAODistribuye();

	
	public abstract DAOProductoCompra getDAOProductoCompra();
}