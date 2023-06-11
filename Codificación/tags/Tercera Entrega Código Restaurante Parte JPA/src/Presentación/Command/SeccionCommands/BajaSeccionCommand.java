package Presentación.Command.SeccionCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BajaSeccionCommand implements Command {

	public Context executeCommand(Object datos) {
		int respuesta = FactoriaSA.getInstance().getSASeccion().eliminarSeccion((int) datos);

		if (respuesta > 0) {
			return new Context(Eventos.RES_BAJA_SECCION_OK, respuesta);
		} else {
			return new Context(Eventos.RES_BAJA_SECCION_KO, respuesta);
		}

	}
}