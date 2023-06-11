package Presentación.Command.CompraCommands;

import Negocio.Compra.TCarrito;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class CerrarCompraCommand implements Command {

	public Context executeCommand(Object datos) {

		int res = FactoriaSA.getInstance().getSACompra().cerrarCompra((TCarrito) datos);
		Context resContext;

		if (res >= 0) {
			resContext = new Context(Eventos.RES_PAGAR_COMPRA_OK, (TCarrito) datos);
		} else {
			resContext = new Context(Eventos.RES_PAGAR_COMPRA_KO, null);
		}

		return resContext;
	}
}