package Presentación.Command.SeccionCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Seccion.TSeccion;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarSeccionCommand implements Command {

	public Context executeCommand(Object datos) {
		TSeccion respuesta = FactoriaSA.getInstance().getSASeccion().buscarSeccion((int) datos);

		if (respuesta != null) {
			return new Context(Eventos.VBUSCAR_SECCION, respuesta);
		} else {
			return new Context(Eventos.RES_BUSCAR_SECCION_KO, respuesta);
		}

	}
}