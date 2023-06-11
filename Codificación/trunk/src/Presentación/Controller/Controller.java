package Presentaci�n.Controller;

import Presentaci�n.Command.Context;

public abstract class Controller {

	private static Controller instancia;

	public static Controller getInstance() {
		if (instancia == null) {
			instancia = new ControllerImp();
		}
		return instancia;
	}

	public abstract void action(Context contexto);
}