package Presentación.Command.ClienteCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarClientePorDniCommand implements Command {

	@Override
	public Context executeCommand(Object datos) {
	     int respuesta = FactoriaSA.getInstance().getSACliente().buscarUnoClientePorDni((String) datos);
	     
	     if (respuesta > 0) {
	    	 return new Context(Eventos.RES_BUSCAR_CLIENTE_POR_DNI_OK, respuesta);
	     } else {
				return new Context(Eventos.RES_BUSCAR_CLIENTE_POR_DNI_KO, null);
	     }
	}

}
