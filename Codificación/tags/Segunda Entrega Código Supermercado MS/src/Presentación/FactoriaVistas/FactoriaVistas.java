package Presentación.FactoriaVistas;

import Presentación.Command.Context;

public abstract class FactoriaVistas {
	
	static private FactoriaVistas instancia;

	public static FactoriaVistas getInstance() {
		if(instancia == null) instancia = new FactoriaVistasImp();
		
		return instancia;
	}

	public abstract IGUI generarVista(Context contexto);
	public abstract IGUI getCurrentView(); 
}