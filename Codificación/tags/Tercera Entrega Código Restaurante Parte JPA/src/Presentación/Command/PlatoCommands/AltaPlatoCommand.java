package Presentaci�n.Command.PlatoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Plato.TPlato;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class AltaPlatoCommand implements Command {

	@Override
	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAPlato().altaPlato((TPlato) datos);
		Context resCtx;
		
		if (res > 0) {
			resCtx = new Context(Eventos.RES_ALTA_PLATO_OK, res);
		} else {
			resCtx = new Context(Eventos.RES_ALTA_PLATO_KO, null);
		}
		
		return resCtx;
	}
}