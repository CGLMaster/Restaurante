package Presentación.Command.ClienteRestCommand;

import Negocio.ClienteRest.TClienteRest;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;


public class AltaClienteRestCommand implements Command {
	
	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAClienteRest().altaClienteRest((TClienteRest)datos);
		if(res != -1){
			return new Context(Eventos.RES_ALTA_CLIENTE_REST_OK,res);
		}else {
			return new Context(Eventos.RES_ALTA_CLIENTE_REST_KO,null);
		}
	}
}