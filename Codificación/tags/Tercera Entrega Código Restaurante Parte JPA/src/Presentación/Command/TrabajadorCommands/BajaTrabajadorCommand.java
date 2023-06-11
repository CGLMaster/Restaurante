package Presentación.Command.TrabajadorCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BajaTrabajadorCommand implements Command {

	@Override
	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSATrabajador().eliminarTrabajador((int) datos);
		Context resContext;

		if (res > 0) {
			resContext = new Context(Eventos.RES_BAJA_TRABAJADOR_OK, datos);
		} else {
			resContext = new Context(Eventos.RES_BAJA_TRABAJADOR_KO, null);
		}

		return resContext;
	}
}