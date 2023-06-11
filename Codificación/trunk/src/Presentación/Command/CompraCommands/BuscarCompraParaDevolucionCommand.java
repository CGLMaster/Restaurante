package Presentaci�n.Command.CompraCommands;

import Negocio.Compra.TCompraConProductos;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class BuscarCompraParaDevolucionCommand implements Command {

	@Override
	public Context executeCommand(Object datos) {
		TCompraConProductos compra = FactoriaSA.getInstance().getSACompra().buscarPorIDCompra((int) datos);
		Context resContext;

		if (compra != null) {
			resContext = new Context(Eventos.CREAR_V_DEVOLUCION, compra);
		} else {
			resContext = new Context(Eventos.RES_BUSCAR_COMPRA_ID_KO, null);
		}

		return resContext;
	}

}
