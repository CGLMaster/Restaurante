package Presentaci�n.Command.SeccionCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Seccion.TSeccion;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;


public class AltaSeccionCommand implements Command {
	
	public Context executeCommand(Object datos) {
		int respuesta = FactoriaSA.getInstance().getSASeccion().altaSeccion((TSeccion) datos);
		
		if(respuesta > 0){
			return new Context(Eventos.RES_ALTA_SECCION_OK,respuesta);
		}else {
			return new Context(Eventos.RES_ALTA_SECCION_KO,respuesta);
		}
		
	}
}