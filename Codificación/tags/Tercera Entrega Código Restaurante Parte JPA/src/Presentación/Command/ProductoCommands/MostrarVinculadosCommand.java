package Presentación.Command.ProductoCommands;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.TDistribuye;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class MostrarVinculadosCommand implements Command{

	@Override
	public Context executeCommand(Object datos) {
		List<TDistribuye> vinculados =FactoriaSA.getInstance().getSAProducto().buscarVinculadosAProd((int) datos);
		Context resContext = null;
		
		if(vinculados.size() == 0){
			resContext = new Context(Eventos.VINCULAR_PRODUCTO_PROVEEDOR, datos);
		}else{
			resContext = new Context(Eventos.MOSTRAR_VINCULADOS, vinculados);
		}
		
		return resContext;
	}

}
