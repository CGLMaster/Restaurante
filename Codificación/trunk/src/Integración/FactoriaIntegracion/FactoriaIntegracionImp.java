package Integraci�n.FactoriaIntegracion;

import Integraci�n.Marca.DAOMarca;
import Integraci�n.Marca.DAOMarcaImp;
import Integraci�n.Seccion.DAOSeccion;
import Integraci�n.Seccion.DAOSeccionImp;
import Integraci�n.Trabajador.DAOTrabajador;
import Integraci�n.Trabajador.DAOTrabajadorImp;
import Integraci�n.Producto.DAOProducto;
import Integraci�n.Producto.DAOProductoImp;
import Integraci�n.Cliente.DAOCliente;
import Integraci�n.Cliente.DAOClienteImp;
import Integraci�n.Compra.DAOCompra;
import Integraci�n.Compra.DAOCompraImp;
import Integraci�n.Proveedor.DAOProveedor;
import Integraci�n.Proveedor.DAOProveedorImp;
import Integraci�n.Producto.DAODistribuye;
import Integraci�n.Producto.DAODistribuyeImp;
import Integraci�n.Compra.DAOProductoCompra;
import Integraci�n.Compra.DAOProductoCompraImp;

public class FactoriaIntegracionImp extends FactoriaIntegracion {

	public DAOMarca getDAOMarca() {
		return new DAOMarcaImp();
	}

	public DAOSeccion getDAOSeccion() {
		return new DAOSeccionImp();
	}

	public DAOTrabajador getDAOTrabajador() {
		return new DAOTrabajadorImp();
	}

	public DAOProducto getDAOProducto() {
		return new DAOProductoImp();
	}

	public DAOCliente getDAOCliente() {
		return new DAOClienteImp();
	}

	public DAOCompra getDAOCompra() {
		return new DAOCompraImp();
	}

	public DAOProveedor getDAOProveedor() {
		return new DAOProveedorImp();
	}

	public DAODistribuye getDAODistribuye() {
		return new DAODistribuyeImp();
	}

	public DAOProductoCompra getDAOProductoCompra() {
		return new DAOProductoCompraImp();
	}
}