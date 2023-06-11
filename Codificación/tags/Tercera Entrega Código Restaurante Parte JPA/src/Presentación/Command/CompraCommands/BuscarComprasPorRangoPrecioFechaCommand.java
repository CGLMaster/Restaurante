package Presentación.Command.CompraCommands;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import Negocio.Compra.TCompra;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarComprasPorRangoPrecioFechaCommand implements Command {
	@SuppressWarnings("unchecked")
	@Override
	public Context executeCommand(Object datos) {
		HashMap<String, Object> args = (HashMap<String, Object>) datos;
		List<TCompra> compras = FactoriaSA.getInstance().getSACompra().buscarComprasPorRangoPrecioFecha(
				(int) args.get("desdePrecio"), (int) args.get("hastaPrecio"), (LocalDate) args.get("desdeFecha"),
				(LocalDate) args.get("hastaFecha"));
		Context resContext;

		if (compras != null) {
			resContext = new Context(Eventos.CREAR_VCOMPRA, compras);
		} else {
			resContext = new Context(Eventos.RES_BUSCAR_COMPRA_POR_PRECIO_FECHA_KO, compras);
		}
		return resContext;
	}
}
