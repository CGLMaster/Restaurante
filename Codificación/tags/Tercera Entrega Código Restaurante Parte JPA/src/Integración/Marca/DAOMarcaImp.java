package Integración.Marca;

import Negocio.Marca.TMarca;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Integración.DataSource.DataSourceSingleton;

public class DAOMarcaImp implements DAOMarca {

	public DAOMarcaImp() {

	}

	public int altaMarca(TMarca marca) {
		int res = 0;

		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			Statement s = c.createStatement();
			res = s.executeUpdate("INSERT INTO MARCA(NOMBRE,WEB,ACTIVO,CONT_PRODUCTOS)" + "VALUES('" + marca.getNombre()
					+ "','" + marca.getWeb() + "',true, 0)", Statement.RETURN_GENERATED_KEYS);

			ResultSet idInserted = s.getGeneratedKeys();

			if (idInserted.next()) {
				res = idInserted.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public TMarca buscarMarca(int id) {
		TMarca m = null;
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
			ResultSet r = s.executeQuery("SELECT * FROM MARCA WHERE ID_MARCA = " + id + slctforUpdate + ";");
			while (r.next()) {
				m = new TMarca(r.getString("NOMBRE"));
				m.setID(r.getInt("ID_MARCA"));
				m.setWeb(r.getString("WEB"));
				m.setActivo(r.getBoolean("ACTIVO"));
				m.setContProductos(r.getInt("CONT_PRODUCTOS"));
			}

			if (t == null) {
				c.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}

	public List<TMarca> buscarTodosMarca() {
		List<TMarca> marcas = null;

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
			ResultSet r = s.executeQuery("SELECT * FROM MARCA WHERE ACTIVO = 1;");
			marcas = new ArrayList<TMarca>();
			while (r.next()) {
				TMarca m = new TMarca(r.getString("NOMBRE"));
				m.setID(r.getInt("ID_MARCA"));
				m.setWeb(r.getString("WEB"));
				m.setActivo(r.getBoolean("ACTIVO"));
				m.setContProductos(r.getInt("CONT_PRODUCTOS"));
				marcas.add(m);
			}

			if (t == null) {
				c.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marcas;
	}

	public int modificarMarca(TMarca marca) {
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			Statement s = c.createStatement();
			int res = s.executeUpdate("UPDATE MARCA SET NOMBRE = '" + marca.getNombre() + "', WEB = '" + marca.getWeb()
					+ "', ACTIVO = TRUE, CONT_PRODUCTOS = " + marca.getContProductos() + " WHERE ID_MARCA = "
					+ marca.getID() + " ;");

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int bajaMarca(int id) {
		int res = 0;
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			Statement s = c.createStatement();

			res = s.executeUpdate("UPDATE MARCA SET ACTIVO = 0 WHERE ID_Marca = " + id + ";",
					Statement.RETURN_GENERATED_KEYS);

			ResultSet idInserted = s.getGeneratedKeys();

			if (idInserted.next()) {
				res = idInserted.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return res;
	}

	public TMarca buscarMarcaPorNombre(String nombre) {
		TMarca aux = null;

		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();

			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM MARCA WHERE NOMBRE= '" + nombre + "';");

			if (r.next()) {
				aux = new TMarca(r.getInt("ID_MARCA"), r.getString("NOMBRE"), r.getString("WEB"),
						r.getInt("CONT_PRODUCTOS"), r.getBoolean("ACTIVO"));
			}

			return aux;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public int reactivar(int id_marca) {
		try {
			Connection c = (Connection) TransactionManager.getInstance().getTransaccion().getResource();
			Statement s = c.createStatement();
			int res = s.executeUpdate("UPDATE MARCA SET ACTIVO = TRUE WHERE ID_MARCA = " + id_marca + " ;");

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}