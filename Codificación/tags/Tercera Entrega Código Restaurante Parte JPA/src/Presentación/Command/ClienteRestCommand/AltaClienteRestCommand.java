package Presentaci�n.Command.ClienteRestCommand;

import Negocio.ClienteRest.TClienteRest;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;


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