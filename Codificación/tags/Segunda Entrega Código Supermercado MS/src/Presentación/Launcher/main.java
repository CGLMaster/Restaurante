package Presentación.Launcher;

import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;

public class main {

	public static void main(String[] args) {
		Context c = new Context(Eventos.CREAR_VPRINCIPAL); 
		Controller.getInstance().action(c);
	}

}
