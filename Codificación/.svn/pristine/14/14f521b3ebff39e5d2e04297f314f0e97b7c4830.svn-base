package Integración.Queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Integración.DataSource.DataSourceSingleton;
import Negocio.Compra.TCompra;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;

public class ComprasPorRangoFechaPrecio implements Query {

	public Object Execute(Object params) {
		HashMap<String, Object> args = (HashMap<String, Object>) params;
		return buscarComprasPorRangoFechaPrecio((int) args.get("desdePrecio"), (int) args.get("hastaPrecio"),
				(LocalDate) args.get("desdeFecha"), (LocalDate) args.get("hastaFecha"));
	}

	public List<TCompra> buscarComprasPorRangoFechaPrecio(int desdePrecio, int hastaPrecio, LocalDate desdeFecha,
			LocalDate hastaFecha) {
		List<TCompra> res = new ArrayList<>();

		try {
			Transaction t = null;
			Connection c;
			String slctforUpdate = "";

			try {
				t = TransactionManager.getInstance().getTransaccion();
				c = (Connection) t.getResource();
				slctforUpdate = " FOR UPDATE"; //

			} catch (Exception e) {
				c = DataSourceSingleton.getInstancia().getNewConnection();
			}
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM COMPRA WHERE ACTIVO = 1 " + " AND PRECIO_TOTAL BETWEEN "
					+ desdePrecio + " AND " + hastaPrecio + " AND FECHA >= '" + desdeFecha.toString()
					+ "' AND FECHA <='" + hastaFecha.toString() + "'" + slctforUpdate + " ;");

			while (r.next()) {
				int id = r.getInt("ID_COMPRA");
				float precio = r.getFloat("PRECIO_TOTAL");
				int idCliente = r.getInt("ID_SOCIO");
				int idTrabajador = r.getInt("ID_TRABAJADOR");
				boolean esActivo = r.getBoolean("ACTIVO");
				String fecha = r.getString("FECHA");
				LocalDate date = null;
				date = date.parse(fecha);
				TCompra compra = new TCompra(id, date, idCliente, idTrabajador, precio);
				compra.setActivo(esActivo);
				res.add(compra);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

}