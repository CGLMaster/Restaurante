package Presentaci�n.Command.MarcaCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class BajaMarcaCommand implements Command {

	public Context executeCommand(Object datos) {

		int res = FactoriaSA.getInstance().getSAMarca().eliminarMarca((int) datos);
		Context resContext;

		if (res > 0) {
			resContext = new Context(Eventos.RES_BAJA_MARCA_OK, datos);
		} else {
			resContext = new Context(Eventos.RES_BAJA_MARCA_KO);
		}

		return resContext;
	}
}