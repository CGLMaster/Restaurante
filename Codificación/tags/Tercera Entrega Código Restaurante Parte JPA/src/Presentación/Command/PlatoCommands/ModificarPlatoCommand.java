package Presentaci�n.Command.PlatoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Plato.TPlato;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class ModificarPlatoCommand implements Command {

	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAPlato().modificarPlato((TPlato) datos);
		Context resContext;

		if (res > 0) {
			resContext = new Context(Eventos.RES_MODIFICAR_PLATO_OK, res);
		} else {
			resContext = new Context(Eventos.RES_MODIFICAR_PLATO_KO, null);
		}

		return resContext;
	}
}