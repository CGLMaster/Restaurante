package Integración.Producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Integración.DataSource.DataSourceSingleton;
import Negocio.Producto.TProdNoPerecedero;
import Negocio.Producto.TProdPerecedero;
import Negocio.Producto.TProducto;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;

public class DAOProductoImp implements DAOProducto {


	public int altaProducto(TProducto p) {
		int exito = -1;

		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection c = (Connection) t.getResource();
			Statement s = c.createStatement();
			exito = s.executeUpdate("INSERT INTO PRODUCTO " + "(PRECIO,STOCK,NOMBRE,ID_MARCA,ID_SECCION,ACTIVO) "
					+ "VALUES (" + p.getPrecio() + "," + p.getStock() + ",'" + p.getNombre() + "'," + p.getIDMarca()
					+ "," + p.getIDSeccion() + ",true);", Statement.RETURN_GENERATED_KEYS);

			ResultSet idInserted = s.getGeneratedKeys();
			int id = 0;
			if (idInserted.next()) {
				id = idInserted.getInt(1);
			}
			exito = id;
			if (p instanceof TProdNoPerecedero) {
				TProdNoPerecedero noperec = (TProdNoPerecedero) p;
				exito = s.executeUpdate("INSERT INTO NO_PERECEDERO (ID_PRODUCTO,TIPO) " + "VALUES(" + exito
						+ ",'" + noperec.getTipo() + "');");
			} else if (p instanceof TProdPerecedero) {
				TProdPerecedero perec = (TProdPerecedero) p;
				exito = s.executeUpdate("INSERT INTO PERECEDERO (ID_PRODUCTO,FECHA_DE_CADUCIDAD) " + "VALUES("
						+ exito + ",'" + perec.getFechaDeCaducidad().toString() + "');");
			}
			exito = id;

		} catch (Exception e) {

			e.printStackTrace();
			exito = -1;
		}
		return exito;
	}

	public int bajaProducto(int prod) {
		int r = -1;
		try {
			Transaction t = TransactionManager.getInstance().getTransaccion();
			Connection c = (Connection) t.getResource();
			Statement s = c.createStatement();

			r = s.executeUpdate("UPDATE PRODUCTO SET ACTIVO = FALSE WHERE ID_Producto = " + prod + ";");
			r = prod;

		} catch (Exception e) {
			e.printStackTrace();
			r = -1;
		}
		return r;
	}

	public int modificarProducto(TProducto prod) {
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			Statement s = c.createStatement();
			int res = s.executeUpdate("UPDATE PRODUCTO SET NOMBRE = '" + prod.getNombre() + "', PRECIO = "
					+ prod.getPrecio() + ", STOCK = " + prod.getStock() + ", ID_MARCA = " + prod.getIDMarca()
					+ ", ID_SECCION = " + prod.getIDSeccion() + ", ACTIVO = TRUE WHERE ID_PRODUCTO = "
					+ prod.getIDProducto() + " ;");
			
			s = c.createStatement();
			if (prod instanceof TProdNoPerecedero) {
				res = s.executeUpdate("UPDATE NO_PERECEDERO SET TIPO = '" + ((TProdNoPerecedero) prod).getTipo() + "' WHERE ID_PRODUCTO = "
						+ prod.getIDProducto() + " ;");
				
			} else if (prod instanceof TProdPerecedero) {
				res = s.executeUpdate("UPDATE PERECEDERO SET FECHA_DE_CADUCIDAD = '" + ((TProdPerecedero) prod).getFechaDeCaducidad().toString() + "' WHERE ID_PRODUCTO = "
						+ prod.getIDProducto() + " ;");
			}

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public TProducto buscarProducto(int id) {
		TProducto pr = null;
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
					"SELECT * FROM PERECEDERO PE JOIN PRODUCTO PR ON PR.ID_PRODUCTO=PE.ID_PRODUCTO WHERE PR.ID_PRODUCTO = "
							+ id + slctforUpdate + ";");

			if (r != null && r.first()) {
				int idPr = r.getInt("ID_PRODUCTO");
				String nombre = r.getString("NOMBRE");
				Float precio = r.getFloat("PRECIO");
				int stock = r.getInt("STOCK");
				int idMarca = r.getInt("ID_MARCA");
				int idSecc = r.getInt("ID_SECCION");
				boolean activo = r.getBoolean("ACTIVO");
				String fecha = r.getString("FECHA_DE_CADUCIDAD");
				LocalDate date = null;
				date = date.parse(fecha);
				pr = new TProdPerecedero(idPr, nombre, stock, precio, idMarca, idSecc, activo, date);
			} else {

				r = s.executeQuery(
						"SELECT * FROM NO_PERECEDERO PN JOIN PRODUCTO PR ON PR.ID_PRODUCTO=PN.ID_PRODUCTO WHERE PR.ID_PRODUCTO = "
								+ id + slctforUpdate + ";");

				r.first();
				int idPr = r.getInt("ID_PRODUCTO");
				String nombre = r.getString("NOMBRE");
				Float precio = r.getFloat("PRECIO");
				int stock = r.getInt("STOCK");
				int idMarca = r.getInt("ID_MARCA");
				int idSecc = r.getInt("ID_SECCION");
				boolean activo = r.getBoolean("ACTIVO");
				String tipo = r.getString("TIPO");
				pr = new TProdNoPerecedero(idPr, nombre, stock, precio, idMarca, idSecc, activo, tipo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pr;
	}

	public List<TProducto> buscarTodosProducto() {
		List<TProducto> productos = null;
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
							+ slctforUpdate + ";");
			productos = new ArrayList<TProducto>();
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
				productos.add(new TProdPerecedero(id, nombre, stock, precio, idMarca, idSeccion, esActivo, date));
			}
			r = s.executeQuery(
					"SELECT * FROM NO_PERECEDERO NP JOIN PRODUCTO PR ON PR.ID_PRODUCTO=NP.ID_PRODUCTO WHERE PR.ACTIVO = 1 "
							+ slctforUpdate + ";");
			while (r.next()) {
				int id = r.getInt("ID_PRODUCTO");
				float precio = r.getFloat("PRECIO");
				int stock = r.getInt("STOCK");
				String nombre = r.getString("NOMBRE");
				int idMarca = r.getInt("ID_MARCA");
				int idSeccion = r.getInt("ID_SECCION");
				boolean esActivo = r.getBoolean("ACTIVO");
				String tipo = r.getString("TIPO");
				productos.add(new TProdNoPerecedero(id, nombre, stock, precio, idMarca, idSeccion, esActivo, tipo));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productos;
	}

	@Override
	public int reactivar(int id_producto) {
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			Statement s = c.createStatement();
			int res = s.executeUpdate("UPDATE MARCA SET ACTIVO = TRUE WHERE ID_PRODUCTO = " + id_producto + " ;");

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public TProducto buscarProductoPorMarcayNombre(String nombre_prod, int id_marca) {
		TProducto p = null;
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

			Statement s = c.createStatement();

			ResultSet r = s.executeQuery(
					"SELECT * FROM PERECEDERO PE JOIN PRODUCTO P ON PE.ID_PRODUCTO = P.ID_PRODUCTO WHERE P.NOMBRE = '"
							+ nombre_prod + "' AND ID_MARCA = " + id_marca+ " ;");
			if (r.next()) {
				String fecha1 = r.getString("FECHA_DE_CADUCIDAD");
				LocalDate date = null;
				date = date.parse(fecha1);
				p = new TProdPerecedero(r.getInt("ID_PRODUCTO"), r.getString("NOMBRE"), r.getInt("STOCK"),
						r.getFloat("PRECIO"), r.getInt("ID_MARCA"), r.getInt("ID_SECCION"), r.getBoolean("ACTIVO"),
						date);
			} else {
				ResultSet r2 = s.executeQuery(
						"SELECT * FROM NO_PERECEDERO PN JOIN PRODUCTO P ON PN.ID_PRODUCTO = P.ID_PRODUCTO WHERE P.NOMBRE = '"
							+ nombre_prod + "' AND ID_MARCA = " + id_marca+ " ;");
				if (r2.next()) {
					p = new TProdNoPerecedero(r2.getInt("ID_PRODUCTO"), r2.getString("NOMBRE"), r2.getInt("STOCK"),
							r2.getFloat("PRECIO"), r2.getInt("ID_MARCA"), r2.getInt("ID_SECCION"), r2.getBoolean("ACTIVO"),
							r2.getString("TIPO"));
				}
			}

			return p;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int reducirStock(int ID_Producto, int cantidad) {
		int r;

		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			Statement s = c.createStatement();

			r = s.executeUpdate(
					"UPDATE PRODUCTO SET STOCK = STOCK - " + cantidad + " WHERE ID_PRODUCTO = " + ID_Producto);

		} catch (Exception e) {
			r = -1;
			e.printStackTrace();
		}

		return r;
	}

}