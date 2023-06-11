package Presentación.Command.TrabajadorCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Trabajador.TTrabajador;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarTrabajadorPorDNICommand implements Command {

	@Override
	public Context executeCommand(Object datos) {
		TTrabajador trabajador = FactoriaSA.getInstance().getSATrabajador().buscarDatosTrabajadorPorDNI((String) datos);
		Context resContext;

		if (trabajador != null) {
			resContext = new Context(Eventos.BUSCAR_TRABAJADOR_DNI, trabajador);
		} else {
			resContext = new Context(Eventos.RES_BUSCAR_TRABAJADOR_DNI_KO, null);
		}
		
		return resContext;
	}

}
