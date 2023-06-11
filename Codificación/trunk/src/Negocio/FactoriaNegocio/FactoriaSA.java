package Negocio.FactoriaNegocio;

import Negocio.Marca.SAMarca;
import Negocio.Pedido.SAPedido;
import Negocio.Personal.SAPersonal;
import Negocio.Plato.SAPlato;
import Negocio.Compra.SACompra;
import Negocio.Ingrediente.SAIngrediente;
import Negocio.Cliente.SACliente;
import Negocio.ClienteRest.SAClienteRest;
import Negocio.Trabajador.SATrabajador;
import Negocio.Turno.SATurno;
import Negocio.Producto.SAProducto;
import Negocio.Proveedor.SAProveedor;
import Negocio.Seccion.SASeccion;

public abstract class FactoriaSA {

	private static FactoriaSA instancia;

	public static FactoriaSA getInstance() {
		if (instancia == null)
			instancia = new FactoriaSAImp();
		return instancia;
	}

	public abstract SAMarca getSAMarca();

	public abstract SACompra getSACompra();

	public abstract SACliente getSACliente();

	public abstract SATrabajador getSATrabajador();

	public abstract SAProducto getSAProducto();

	public abstract SAProveedor getSAProveedor();

	public abstract SASeccion getSASeccion();

	public abstract SAClienteRest getSAClienteRest();

	public abstract SAPedido getSAPedido();

	public abstract SAPlato getSAPlato();

	public abstract SAPersonal getSAPersonal();
	
	public abstract SAIngrediente getSAIngrediente();
	
	public abstract SATurno getSATurno();

	
}