package Presentaci�n.Command.PlatoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Plato.TPlato;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class BuscarPlatoCommand implements Command {

	public Context executeCommand(Object datos) {
		TPlato plato = FactoriaSA.getInstance().getSAPlato().buscarPlato((int) datos);
		Context resContext;

		if (plato != null) {
			resContext = new Context(Eventos.BUSCAR_PLATO, plato);
		} else {
			resContext = new Context(Eventos.RES_BUSCAR_PLATO_KO, plato);
		}

		return resContext;
	}
}