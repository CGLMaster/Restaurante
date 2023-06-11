package Presentaci�n.FactoriaVistas;

import Presentaci�n.Command.Context;

public abstract class FactoriaVistas {
	
	static private FactoriaVistas instancia;

	public static FactoriaVistas getInstance() {
		if(instancia == null) instancia = new FactoriaVistasImp();
		
		return instancia;
	}

	public abstract IGUI generarVista(Context contexto);
	public abstract IGUI getCurrentView(); 
}