package Integración.FactoriaIntegracion;

import Integración.Marca.DAOMarca;
import Integración.Seccion.DAOSeccion;
import Integración.Trabajador.DAOTrabajador;
import Integración.Producto.DAOProducto;
import Integración.Cliente.DAOCliente;
import Integración.Compra.DAOCompra;
import Integración.Proveedor.DAOProveedor;
import Integración.Producto.DAODistribuye;
import Integración.Compra.DAOProductoCompra;


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