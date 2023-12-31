
package Presentación.Command.TurnoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.TTurno;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarTurnoCommand implements Command {

	public Context executeCommand(Object datos) {
		TTurno res = FactoriaSA.getInstance().getSATurno().buscarUnTurno((int) datos);
		Context resContext;

		if (res != null) {
			resContext = new Context(Eventos.BUSCAR_TURNO, res);
		} else {
			resContext = new Context(Eventos.RES_BUSCAR_TURNO_KO, datos);
		}

		return resContext;
	}
}