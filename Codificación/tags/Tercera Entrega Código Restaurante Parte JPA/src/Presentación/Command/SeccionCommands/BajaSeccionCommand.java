package Presentaci�n.Command.SeccionCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

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