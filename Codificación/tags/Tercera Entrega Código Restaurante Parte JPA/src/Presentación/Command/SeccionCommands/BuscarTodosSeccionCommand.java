package Presentaci�n.Command.SeccionCommands;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Seccion.TSeccion;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

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