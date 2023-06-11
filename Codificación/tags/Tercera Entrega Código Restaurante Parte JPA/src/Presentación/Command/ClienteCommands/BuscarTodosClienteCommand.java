package Presentación.Command.ClienteCommands;

import java.util.List;

import Negocio.Cliente.TCliente;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;


public class BuscarTodosClienteCommand implements Command {
	
	public Context executeCommand(Object datos) {
        List<TCliente> respuesta = FactoriaSA.getInstance().getSACliente().buscarTodosCliente();
		
		if(respuesta != null ){
			return new Context(Eventos.CREAR_VCLIENTE,respuesta);
		}else {
			return new Context(Eventos.RES_BUSCAR_TODOS_CLIENTE_KO,null);
		}
		
	}
}