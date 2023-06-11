package Presentación.Command.TurnoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.TTurno;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class ModificarTurnoCommand implements Command {
	
	public Context executeCommand(Object datos) {
		int res=FactoriaSA.getInstance().getSATurno().modificarTurno((TTurno) datos);
		if (res != -1) {
			return new Context(Eventos.RES_MODIFICAR_TURNO_OK, res);
		} else {
			return new Context(Eventos.RES_MODIFICAR_TURNO_KO, null);
		}
	}
}