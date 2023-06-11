package Integración.Compra;

import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;
import Negocio.Compra.TLineaDeCompra;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integración.DataSource.DataSourceSingleton;

public class DAOProductoCompraImp implements DAOProductoCompra {

	

	public int aniadirProductoCompra(TLineaDeCompra lineaCompra) {
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			Statement s = c.createStatement();
			int res = s.executeUpdate("INSERT INTO LINEADECOMPRA (ID_COMPRA, ID_PRODUCTO, CANTIDAD, PRECIO) VALUES "
					+ "(" + lineaCompra.getID_Compra() + ", " + lineaCompra.getIDProducto() + ", "
					+ lineaCompra.getCantidad() + ", " + lineaCompra.getPrecio() + ");");

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int modificarProductoCompra(TLineaDeCompra lineaCompra) {

		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			Statement s = c.createStatement();
			int res = s.executeUpdate(
					"UPDATE LINEADECOMPRA SET CANTIDAD = '" + lineaCompra.getCantidad() + "' WHERE ID_COMPRA = "
							+ lineaCompra.getID_Compra() + " AND ID_PRODUCTO = " + lineaCompra.getIDProducto() + ";");

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int eliminarProductoCompra(int idCompra, int idProducto) {
		TransactionManager tManager = TransactionManager.getInstance();
		int exito = -1;
		try {
			Transaction t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			Statement s = c.createStatement();
			exito = s.executeUpdate(
					"DELETE FROM LINEADECOMPRA WHERE ID_COMPRA = " + idCompra + " AND ID_PRODUCTO = " + idProducto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	public TLineaDeCompra leerLinea(int id_Compra, int id_Producto) {
		TLineaDeCompra lineaCompra = null;
		try {
			Transaction t = null;
			Connection c;
			try {
				t = TransactionManager.getInstance().getTransaccion();
				c = (Connection) t.getResource();

			} catch (Exception e) {
				c = DataSourceSingleton.getInstancia().getNewConnection();
			}

			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM LINEADECOMPRA WHERE ID_COMPRA = " + id_Compra
					+ " AND ID_PRODUCTO = " + id_Producto + ";");

			r.next();
			lineaCompra = new TLineaDeCompra();
			lineaCompra.setID_Compra(r.getInt("ID_COMPRA"));
			lineaCompra.setIDProducto(r.getInt("ID_PRODUCTO"));
			lineaCompra.setCantidad(r.getInt("CANTIDAD"));
			lineaCompra.setPrecio(r.getFloat("PRECIO"));

			if (t == null) {
				c.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			lineaCompra = null;
		}
		return lineaCompra;
	}

	public List<TLineaDeCompra> leerTodasLineas(int idCompra) {
		List<TLineaDeCompra> lineaDeCompras = null;

		try {
			Transaction t = null;
			Connection c;
			try {
				t = TransactionManager.getInstance().getTransaccion();
				c = (Connection) t.getResource();
			} catch (Exception e) {
				c = DataSourceSingleton.getInstancia().getNewConnection();
			}

			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM LINEADECOMPRA WHERE ID_COMPRA = " + idCompra + ";");
			lineaDeCompras = new ArrayList<TLineaDeCompra>();
			while (r.next()) {
				TLineaDeCompra lineaCompra = new TLineaDeCompra();
				lineaCompra = new TLineaDeCompra();
				lineaCompra.setID_Compra(r.getInt("ID_COMPRA"));
				lineaCompra.setIDProducto(r.getInt("ID_PRODUCTO"));
				lineaCompra.setCantidad(r.getInt("CANTIDAD"));
				lineaCompra.setPrecio(r.getFloat("PRECIO"));
				lineaDeCompras.add(lineaCompra);
			}

			if (t == null) {
				c.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lineaDeCompras;
	}

}