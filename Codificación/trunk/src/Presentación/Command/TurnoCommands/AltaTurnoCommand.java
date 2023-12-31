package Presentación.Command.TurnoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.TTurno;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class AltaTurnoCommand implements Command {

	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSATurno().altaTurno((TTurno) datos);
		Context resContext;

		if (res > 0) {
			resContext = new Context(Eventos.RES_ALTA_TURNO_OK, res);
		} else {
			resContext = new Context(Eventos.RES_ALTA_TURNO_KO, null);
		}
		return resContext;

	}
}