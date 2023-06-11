package Presentación.Command.ClienteRestCommand;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;


public class EliminarClienteRestCommand implements Command {
	
	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAClienteRest().bajaClienteRest((int) datos);
		if(res != -1){
			return new Context(Eventos.RES_BAJA_CLIENTE_REST_OK,res);
		}else {
			return new Context(Eventos.RES_BAJA_CLIENTE_REST_KO,null);
		}
	}
}