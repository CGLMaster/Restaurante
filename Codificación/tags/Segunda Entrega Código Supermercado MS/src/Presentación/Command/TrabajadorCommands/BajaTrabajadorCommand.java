package Presentaci�n.Command.TrabajadorCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

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