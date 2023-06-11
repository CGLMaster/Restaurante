package Presentación.Command.TrabajadorCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Trabajador.TTrabajador;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class ModificarTrabajadorCommand implements Command {

	@Override
	public Context executeCommand(Object datos) {

		int res = FactoriaSA.getInstance().getSATrabajador().modificarDatosTrabajador((TTrabajador) datos);

		if (res != -1) {
			return new Context(Eventos.RES_MODIFICAR_TRABAJADOR_OK, null);
		} else {
			return new Context(Eventos.RES_MODIFICAR_TRABAJADOR_KO, null);
		}
	}

}
