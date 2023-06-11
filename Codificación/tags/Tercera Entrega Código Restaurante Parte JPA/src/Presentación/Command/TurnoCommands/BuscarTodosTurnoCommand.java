package Presentación.Command.TurnoCommands;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.TTurno;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarTodosTurnoCommand implements Command {

	public Context executeCommand(Object datos) {
		Context result;
		List<TTurno> res = (List<TTurno>) FactoriaSA.getInstance().getSATurno().buscarTodosTurno();

		if (res != null) {
			result = new Context(Eventos.CREAR_VTURNO, res);
		} else {
			result = new Context(Eventos.RES_BUSCAR_TODOS_TURNO_KO, null);
		}

		return result;
	}
}