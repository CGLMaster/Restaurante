package Presentaci�n.Command.ProductoCommands;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.TDistribuye;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

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
