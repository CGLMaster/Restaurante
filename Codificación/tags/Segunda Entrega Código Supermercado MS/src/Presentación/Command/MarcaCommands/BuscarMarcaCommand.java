package Presentación.Command.MarcaCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Marca.TMarca;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;


public class BuscarMarcaCommand implements Command {
	
	public Context executeCommand(Object datos) {
		TMarca res = FactoriaSA.getInstance().getSAMarca().buscarMarca((int) datos);
		Context resContext;
		if(res != null) resContext = new Context(Eventos.BUSCAR_MARCA, res);
		else resContext = new Context(Eventos.RES_BUSCAR_MARCA_KO);
		return resContext;
	}
}