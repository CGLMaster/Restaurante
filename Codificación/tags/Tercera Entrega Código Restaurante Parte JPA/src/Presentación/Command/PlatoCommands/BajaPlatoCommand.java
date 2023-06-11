package Presentación.Command.PlatoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BajaPlatoCommand implements Command {

	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAPlato().bajaPlato((int) datos);
		Context resContext;
		
		if (res > 0) {
			resContext = new Context(Eventos.RES_BAJA_PLATO_OK, datos);
		} else {
			resContext = new Context(Eventos.RES_BAJA_PLATO_KO, null);
		}
		
		return resContext;
	}
}