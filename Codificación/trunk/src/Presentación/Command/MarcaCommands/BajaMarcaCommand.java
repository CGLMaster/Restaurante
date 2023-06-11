package Presentación.Command.MarcaCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

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