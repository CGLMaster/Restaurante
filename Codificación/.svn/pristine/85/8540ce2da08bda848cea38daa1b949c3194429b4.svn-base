package Presentación.Command.ClienteRestCommand;

import Negocio.ClienteRest.TClienteRest;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;


public class BuscarUnClienteRestCommand implements Command {
	
	public Context executeCommand(Object datos) {
		TClienteRest res = FactoriaSA.getInstance().getSAClienteRest().buscarUnClienteRest((int) datos);
		if(res != null){
			return new Context(Eventos.BUSCAR_CLIENTE_REST,res);
		}else {
			return new Context(Eventos.RES_BUSCAR_CLIENTE_REST_KO,null);
		}
	}
}