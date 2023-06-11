package Presentaci�n.Command.TrabajadorCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Trabajador.TTrabajador;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

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
