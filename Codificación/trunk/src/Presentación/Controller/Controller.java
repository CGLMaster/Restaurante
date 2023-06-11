package Presentación.Controller;

import Presentación.Command.Context;

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