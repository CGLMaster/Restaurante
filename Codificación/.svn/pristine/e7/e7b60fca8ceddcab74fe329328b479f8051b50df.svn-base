package Presentación.Command.CompraCommands;

import java.util.List;

import Negocio.Compra.TCompra;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarTodosCompraCommand implements Command {

	@Override
	public Context executeCommand(Object datos) {
		List<TCompra> compras = FactoriaSA.getInstance().getSACompra().buscarTodosCompra();
		Context resContext;

		if (compras != null) {
			resContext = new Context(Eventos.CREAR_VCOMPRA, compras);
		} else {
			resContext = new Context(Eventos.RES_BUSCAR_TODOS_COMPRA_KO, compras);
		}
		return resContext;
	}

}
