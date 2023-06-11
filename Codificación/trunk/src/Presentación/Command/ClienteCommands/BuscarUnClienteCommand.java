package Presentación.Command.ClienteCommands;

import Negocio.Cliente.TCliente;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarUnClienteCommand implements Command {

	@Override
	public Context executeCommand(Object datos) {
	     TCliente respuesta = FactoriaSA.getInstance().getSACliente().buscarUnoCliente((int) datos);
	     
	     if (respuesta != null) {
	    	 return new Context(Eventos.VBUSCAR_CLIENTE, respuesta);
	     } else {
				return new Context(Eventos.RES_BUSCAR_CLIENTE_KO, null);
	     }
	}

}
