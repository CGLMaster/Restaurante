package Presentación.Command.ProductoCommands;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.TProducto;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

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
