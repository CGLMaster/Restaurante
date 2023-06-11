package Presentación.Command.CompraCommands;

import java.util.HashMap;

import Negocio.Compra.TCarrito;
import Negocio.Compra.TLineaDeCompra;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class EliminarProductoCompraCommand implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public Context executeCommand(Object datos) {
		HashMap<String, Object> args = (HashMap<String, Object>) datos;
		int res = FactoriaSA.getInstance().getSACompra().eliminarProducto((TLineaDeCompra) args.get("LineaDeCompra"), (TCarrito) args.get("Carrito"));

		Context aux;
		if (res >= 0) {
			aux = new Context(Eventos.RES_ELIMINAR_PRODUCTO_COMPRA_OK, args.get("Carrito"));
		} else {
			aux = new Context(Eventos.RES_ELIMINAR_PRODUCTO_COMPRA_KO);
		}

		return aux;
	}

	
}
