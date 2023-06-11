package Integración.Queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Integración.DataSource.DataSourceSingleton;
import Negocio.Producto.TProdNoPerecedero;
import Negocio.Producto.TProdPerecedero;
import Negocio.Producto.TProducto;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;

public class ProductosPorRangoPrecio implements Query {

	public Object Execute(Object params) {
		HashMap<String, Object> args = (HashMap<String, Object>) params;
		return buscarProductosPorRangoPrecio((int) args.get("desde"), (int) args.get("hasta"));
	}

	public List<TProducto> buscarProductosPorRangoPrecio(int desde, int hasta) {
		List<TProducto> res = new ArrayList<>();

		try {
			Transaction t = null;
			Connection c;
			String slctforUpdate = "";

			try {
				t = TransactionManager.getInstance().getTransaccion();
				c = (Connection) t.getResource();
				slctforUpdate = " FOR UPDATE";

			} catch (Exception e) {
				c = DataSourceSingleton.getInstancia().getNewConnection();
			}
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(
					"SELECT * FROM PERECEDERO PE JOIN PRODUCTO PR ON PR.ID_PRODUCTO=PE.ID_PRODUCTO WHERE PR.ACTIVO = 1 "
							+ " AND PRECIO BETWEEN " + desde + " AND " + hasta + slctforUpdate + " ;");

			while (r.next()) {
				int id = r.getInt("ID_PRODUCTO");
				float precio = r.getFloat("PRECIO");
				int stock = r.getInt("STOCK");
				String nombre = r.getString("NOMBRE");
				int idMarca = r.getInt("ID_MARCA");
				int idSeccion = r.getInt("ID_SECCION");
				boolean esActivo = r.getBoolean("ACTIVO");
				String fecha = r.getString("FECHA_DE_CADUCIDAD");
				LocalDate date = null;
				date = date.parse(fecha);
				res.add(new TProdPerecedero(id, nombre, stock, precio, idMarca, idSeccion, esActivo, date));
			}
			r = s.executeQuery(
					"SELECT * FROM NO_PERECEDERO NP JOIN PRODUCTO PR ON PR.ID_PRODUCTO=NP.ID_PRODUCTO WHERE PR.ACTIVO = 1 "
							+ " AND PRECIO BETWEEN " + desde + " AND " + hasta + slctforUpdate + " ;");
			while (r.next()) {
				int id = r.getInt("ID_PRODUCTO");
				float precio = r.getFloat("PRECIO");
				int stock = r.getInt("STOCK");
				String nombre = r.getString("NOMBRE");
				int idMarca = r.getInt("ID_MARCA");
				int idSeccion = r.getInt("ID_SECCION");
				boolean esActivo = r.getBoolean("ACTIVO");
				String tipo = r.getString("TIPO");
				res.add(new TProdNoPerecedero(id, nombre, stock, precio, idMarca, idSeccion, esActivo, tipo));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}
}