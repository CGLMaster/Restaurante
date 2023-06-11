package Presentaci�n.Command.ProductoCommands;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.TProducto;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class BuscarTodosProductoCommand implements Command{

	@Override
	public Context executeCommand(Object datos) {
		List<TProducto> res = FactoriaSA.getInstance().getSAProducto().buscarTodosProducto();
		Context resContext;
		
		if(res != null){
			resContext = new Context(Eventos.CREAR_VPRODUCTO, res);
		}else{
			resContext = new Context(Eventos.RES_BUSCAR_TODOS_PRODUCTO_KO, null);
		}
		
		return resContext;
	}

}
