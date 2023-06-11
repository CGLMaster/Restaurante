package Presentaci�n.Command.TrabajadorCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Trabajador.TTrabajador;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class IdentificarTrabajadorCommand implements Command{

	@Override
	public Context executeCommand(Object datos) {

		TTrabajador res = FactoriaSA.getInstance().getSATrabajador().identificarTrabajador((int) datos);
		Context resContext;
		
		if(res != null){
			resContext = new Context(Eventos.LOGIN, (TTrabajador) res);
		}else{
			resContext = new Context(Eventos.RES_IDENTIFICAR_TRABAJADOR_KO);
		}
		
		return resContext;
	}

}
