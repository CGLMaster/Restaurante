package Presentación.Command.TrabajadorCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Trabajador.TTrabajador;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class AltaTrabajadorCommand implements Command {

	public Context executeCommand(Object datos) {

		int res = FactoriaSA.getInstance().getSATrabajador().altaTrabajador((TTrabajador) datos);
		Context resContext;

		if (res > 0) {
			resContext = new Context(Eventos.RES_ALTA_TRABAJADOR_OK, res);
		} else {
			resContext = new Context(Eventos.RES_ALTA_TRABAJADOR_KO, null);
		}

		return resContext;
	}
}
