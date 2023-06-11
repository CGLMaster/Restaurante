package Presentaci�n.Command.MarcaCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Marca.TMarca;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;


public class BuscarMarcaCommand implements Command {
	
	public Context executeCommand(Object datos) {
		TMarca res = FactoriaSA.getInstance().getSAMarca().buscarMarca((int) datos);
		Context resContext;
		if(res != null) resContext = new Context(Eventos.BUSCAR_MARCA, res);
		else resContext = new Context(Eventos.RES_BUSCAR_MARCA_KO);
		return resContext;
	}
}