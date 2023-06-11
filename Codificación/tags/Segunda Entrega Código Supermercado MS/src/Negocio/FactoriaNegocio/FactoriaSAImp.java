package Negocio.FactoriaNegocio;

import Negocio.Marca.SAMarca;
import Negocio.Marca.SAMarcaImp;
import Negocio.Compra.SACompra;
import Negocio.Compra.SACompraImp;
import Negocio.Cliente.SACliente;
import Negocio.Cliente.SAClienteImp;
import Negocio.Trabajador.SATrabajador;
import Negocio.Trabajador.SATrabajadorImp;
import Negocio.Producto.SAProducto;
import Negocio.Producto.SAProductoImp;
import Negocio.Proveedor.SAProveedor;
import Negocio.Proveedor.SAProveedorImp;
import Negocio.Seccion.SASeccion;
import Negocio.Seccion.SASeccionImp;

public class FactoriaSAImp extends FactoriaSA {
	
	public SAMarca getSAMarca() { return new SAMarcaImp(); }
	
	public SACompra getSACompra() { return new SACompraImp(); }

	public SACliente getSACliente() { return new SAClienteImp(); }

	public SATrabajador getSATrabajador() { return new SATrabajadorImp(); }
	
	public SAProducto getSAProducto() { return new SAProductoImp(); }

	public SAProveedor getSAProveedor() { return new SAProveedorImp(); }
	
	public SASeccion getSASeccion() { return new SASeccionImp(); }
}