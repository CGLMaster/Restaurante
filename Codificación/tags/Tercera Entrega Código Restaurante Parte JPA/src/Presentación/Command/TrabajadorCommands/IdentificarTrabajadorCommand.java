package Presentación.Command.TrabajadorCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Trabajador.TTrabajador;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

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
