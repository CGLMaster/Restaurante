package Integración.FactoriaIntegracion;

import Integración.Marca.DAOMarca;
import Integración.Marca.DAOMarcaImp;
import Integración.Seccion.DAOSeccion;
import Integración.Seccion.DAOSeccionImp;
import Integración.Trabajador.DAOTrabajador;
import Integración.Trabajador.DAOTrabajadorImp;
import Integración.Producto.DAOProducto;
import Integración.Producto.DAOProductoImp;
import Integración.Cliente.DAOCliente;
import Integración.Cliente.DAOClienteImp;
import Integración.Compra.DAOCompra;
import Integración.Compra.DAOCompraImp;
import Integración.Proveedor.DAOProveedor;
import Integración.Proveedor.DAOProveedorImp;
import Integración.Producto.DAODistribuye;
import Integración.Producto.DAODistribuyeImp;
import Integración.Compra.DAOProductoCompra;
import Integración.Compra.DAOProductoCompraImp;

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