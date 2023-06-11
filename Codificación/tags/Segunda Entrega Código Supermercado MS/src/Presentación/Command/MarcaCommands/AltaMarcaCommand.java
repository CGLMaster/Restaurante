package Presentación.Command.MarcaCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Marca.TMarca;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;


public class AltaMarcaCommand implements Command {
	
	public Context executeCommand(Object datos) {
		
		int res = FactoriaSA.getInstance().getSAMarca().altaMarca((TMarca) datos);
		Context resContext;
		
		if(res > 0){
			resContext = new Context(Eventos.RES_ALTA_MARCA_OK, res);
		}else{
			resContext = new Context(Eventos.RES_ALTA_MARCA_KO, null);
		}
		
		return resContext;
	}
}