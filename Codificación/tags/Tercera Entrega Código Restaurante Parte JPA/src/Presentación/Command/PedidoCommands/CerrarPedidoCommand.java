package Presentación.Command.PedidoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pedido.TComanda;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class CerrarPedidoCommand implements Command {

	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAPedido().cerrarPedido((TComanda) datos);
		Context resContext;

		if (res >= 0) {
			resContext = new Context(Eventos.RES_PAGAR_PEDIDO_OK, (TComanda) datos);
		} else {
			resContext = new Context(Eventos.RES_PAGAR_PEDIDO_KO, null);
		}

		return resContext;
	}
}