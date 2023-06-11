package Presentaci�n.Command.TurnoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Turno.TTurno;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

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