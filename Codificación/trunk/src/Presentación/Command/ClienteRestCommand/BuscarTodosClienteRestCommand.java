package Presentación.Command.ClienteRestCommand;

import java.util.List;

import Negocio.ClienteRest.TClienteRest;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;


public class BuscarTodosClienteRestCommand implements Command {
	
	public Context executeCommand(Object datos) {
	     List<TClienteRest> res = FactoriaSA.getInstance().getSAClienteRest().buscarTodosClienteRest();
		if(res != null){
			return new Context(Eventos.CREAR_VCLIENTE_REST,res);
		}else {
			return new Context(Eventos.RES_BUSCAR_TODOS_CLIENTE_REST_KO,null);
		}
	}
}