package Integración.Seccion;

import Negocio.Seccion.TSeccion;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integración.DataSource.DataSourceSingleton;

public class DAOSeccionImp implements DAOSeccion {

	public int altaSeccion(TSeccion seccion) {
		TransactionManager tManager = TransactionManager.getInstance();
		int exito = -1;
		try {
			Transaction t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			Statement s = c.createStatement();
			exito = s.executeUpdate(
					"INSERT INTO SECCION (ZONA,PASILLO,CONT_PRODUCTOS,ACTIVO) VALUES ('" + seccion.getZona() + "',"
							+ seccion.getPasillo() + "," + seccion.getCont_productos() + ",true);",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet r = s.getGeneratedKeys();
			if (r.next()) {
				exito = r.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	public TSeccion buscarSeccion(int id) {
		TSeccion seccion = null;
		ResultSet resultSet;
		Connection connection = null;

		try {
			Transaction t = null;
			String slctforUpdate = "";

			try {
				t = TransactionManager.getInstance().getTransaccion();
				connection = (Connection) t.getResource();
				slctforUpdate = " FOR UPDATE";
			} catch (Exception e) {
				connection = DataSourceSingleton.getInstancia().getNewConnection();
			}
			Statement s = connection.createStatement();

			resultSet = s.executeQuery("SELECT * FROM SECCION WHERE ID_SECCION = '" + id + "'" + slctforUpdate + ";");

			if (resultSet.next()) {
				seccion = new TSeccion(resultSet.getInt("ID_SECCION"));
				seccion.setZona(resultSet.getString("ZONA"));
				seccion.setPasillo(resultSet.getInt("PASILLO"));
				seccion.setCont_productos(resultSet.getInt("CONT_PRODUCTOS"));
				seccion.setActivo(resultSet.getBoolean("ACTIVO"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return seccion;
	}

	

	public int buscarSeccionPorZonaPasillo(String zona, int pasillo) {
		int id = -1;
		ResultSet resultSet;
		Connection connection = null;
		try {
			Transaction t = null;
			String slctforUpdate = "";

			try {
				t = TransactionManager.getInstance().getTransaccion();
				connection = (Connection) t.getResource();
				slctforUpdate = " FOR UPDATE";

			} catch (Exception e) {
				connection = DataSourceSingleton.getInstancia().getNewConnection();
			}
			Statement s = connection.createStatement();

			resultSet = s.executeQuery("SELECT ID_SECCION FROM SECCION WHERE ZONA = '" + zona + "' AND PASILLO = "
					+ pasillo + slctforUpdate + ";");

			if (resultSet.next()) {
				id = resultSet.getInt("ID_SECCION");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public List<TSeccion> buscarTodosSeccion() {
		List<TSeccion> secciones = null;
		ResultSet resultSet;
		Connection connection = null;

		try {
			Transaction t = null;
			String slctforUpdate = "";

			try {
				t = TransactionManager.getInstance().getTransaccion();
				connection = (Connection) t.getResource();
				slctforUpdate = " FOR UPDATE";
			} catch (Exception e) {
				connection = DataSourceSingleton.getInstancia().getNewConnection();
			}
			Statement s = connection.createStatement();

			resultSet = s.executeQuery("SELECT * FROM SECCION WHERE ACTIVO = 1" + slctforUpdate + ";");
			secciones = new ArrayList<TSeccion>();

			while (resultSet.next()) {
				TSeccion seccion = new TSeccion(resultSet.getInt("ID_SECCION"));
				seccion.setZona(resultSet.getString("ZONA"));
				seccion.setPasillo(resultSet.getInt("PASILLO"));
				seccion.setCont_productos(resultSet.getInt("CONT_PRODUCTOS"));
				seccion.setActivo(resultSet.getBoolean("ACTIVO"));
				secciones.add(seccion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return secciones;
	}

	public int modificarSeccion(TSeccion seccion) {
		int exito;

		try {
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.getTransaccion();
			Connection connection = (Connection) transaccion.getResource();

			Statement s = connection.createStatement();

			exito = s.executeUpdate("UPDATE SECCION SET PASILLO = '" + seccion.getPasillo() + "', ZONA = '"
					+ seccion.getZona() + "', CONT_PRODUCTOS = " + seccion.getCont_productos() + " WHERE ID_SECCION = "
					+ seccion.getID_Seccion() + ";");

		} catch (Exception e) {
			exito = -1;
			e.printStackTrace();
		}

		return exito;
	}

	public int bajaSeccion(int id) {
		TransactionManager tManager = TransactionManager.getInstance();
		int exito = -1;
		try {
			Transaction t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			Statement s = c.createStatement();
			exito = s.executeUpdate("UPDATE SECCION SET ACTIVO = 0 WHERE ID_SECCION ='" + id + "';");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	@Override
	public int reactivar(int id_seccion) {
		TransactionManager tManager = TransactionManager.getInstance();
		int exito = -1;
		try {
			Transaction t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			Statement s = c.createStatement();
			exito = s.executeUpdate("UPDATE SECCION SET ACTIVO = 1 WHERE ID_SECCION = " + id_seccion + ";");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}
}