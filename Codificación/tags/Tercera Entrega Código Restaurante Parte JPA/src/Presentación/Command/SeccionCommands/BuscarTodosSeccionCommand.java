package Presentación.Command.SeccionCommands;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Seccion.TSeccion;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarTodosSeccionCommand implements Command {

	public Context executeCommand(Object datos) {
		List<TSeccion> respuesta = FactoriaSA.getInstance().getSASeccion().buscarTodosSeccion();

		if (respuesta != null) {
			return new Context(Eventos.CREAR_VSECCION, respuesta);
		} else {
			return new Context(Eventos.RES_BUSCAR_TODOS_SECCION_KO, null);
		}
	}
}