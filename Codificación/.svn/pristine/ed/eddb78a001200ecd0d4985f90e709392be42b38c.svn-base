package Presentación.Command.CompraCommands;

import java.util.HashMap;

import Negocio.Compra.TCarrito;
import Negocio.Compra.TLineaDeCompra;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class AniadirProductoCommand implements Command {

	@SuppressWarnings("unchecked")
	@Override
	public Context executeCommand(Object datos) {
		HashMap<String, Object> args = (HashMap<String, Object>) datos;
		int res = FactoriaSA.getInstance().getSACompra().aniadirProducto((TLineaDeCompra) args.get("LineaDeCompra"), (TCarrito) args.get("Carrito"));
		Context resContext;
		
		if(res != -1){
			resContext = new Context(Eventos.RES_ANIADIR_PRODUCTO_COMPRA_OK, args.get("Carrito"));
		}else{
			resContext=null;
			resContext = new Context(Eventos.RES_ANIADIR_PRODUCTO_COMPRA_KO);
		}
		
		return resContext;
	}

}
