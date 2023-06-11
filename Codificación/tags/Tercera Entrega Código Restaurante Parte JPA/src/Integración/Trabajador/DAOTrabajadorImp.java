package Integración.Trabajador;

import Negocio.Trabajador.TTrabajador;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integración.DataSource.DataSourceSingleton;

public class DAOTrabajadorImp implements DAOTrabajador {

	public int altaTrabajador(TTrabajador trabajador) {
		int r;

		try {
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.getTransaccion();
			Connection connection = (Connection) transaccion.getResource();

			Statement s = connection.createStatement();

			r = s.executeUpdate(
					"INSERT INTO TRABAJADORES(NOMBRE, DNI, ACTIVO ,MAIL) VALUES('" + trabajador.getNombre() + "', '"
							+ trabajador.getDNI() + "',1 ,'" + trabajador.getMail() + "')",
					Statement.RETURN_GENERATED_KEYS);

			ResultSet idInserted = s.getGeneratedKeys();

			if (idInserted.next()) {
				r = idInserted.getInt(1);
			}
		} catch (Exception e) {
			r = -1;
			e.printStackTrace();
		}

		return r;
	}

	public int bajaTrabajador(int id) {
		int r;

		try {
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.getTransaccion();
			Connection connection = (Connection) transaccion.getResource();

			Statement s = connection.createStatement();
			r = s.executeUpdate("UPDATE TRABAJADORES SET ACTIVO = 0 WHERE ID_TRABAJADOR =  " + id + ";");
		} catch (Exception e) {
			return -1;
		}
		return r;
	}

	public int modificarTrabajador(TTrabajador trabajador) {
		int r;

		try {
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.getTransaccion();
			Connection connection = (Connection) transaccion.getResource();

			Statement s = connection.createStatement();

			r = s.executeUpdate("UPDATE TRABAJADORES SET NOMBRE = '" + trabajador.getNombre() + "', DNI = '"
					+ trabajador.getDNI() + "', MAIL ='" + trabajador.getMail() + "' WHERE ID_TRABAJADOR = "
					+ trabajador.getIDTrabajador() + ";");
		} catch (Exception e) {
			r = -1;
			e.printStackTrace();
		}

		return r;
	}


	public List<TTrabajador> buscarTodosTrabajador() {
		List<TTrabajador> trabajadores = null;

		try {
			Connection connection = DataSourceSingleton.getInstancia().getNewConnection();

			Statement s = connection.createStatement();
			ResultSet result = s.executeQuery("SELECT * FROM TRABAJADORES WHERE ACTIVO = 1");
			trabajadores = new ArrayList<TTrabajador>();

			while (result.next()) {
				TTrabajador t = new TTrabajador(result.getString("NOMBRE"), result.getString("MAIL"),
						result.getString("DNI"));
				t.setActivo(result.getBoolean("ACTIVO"));
				t.setId(result.getInt("ID_TRABAJADOR"));
				trabajadores.add(t);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return trabajadores;
	}

	public TTrabajador buscarDatosTrabajadorID(int id) {
		TTrabajador trabajador = null;
		try {

			Transaction t = null;
			Connection connection;
			String slctforUpdate = "";

			try {
				t = TransactionManager.getInstance().getTransaccion();
				connection = (Connection) t.getResource();
				slctforUpdate = " FOR UPDATE";

			} catch (Exception e) {
				connection = DataSourceSingleton.getInstancia().getNewConnection();
			}

			Statement s = connection.createStatement();

			ResultSet r = s.executeQuery("SELECT * FROM TRABAJADORES WHERE ID_TRABAJADOR =" + id + slctforUpdate);

			if (r.next()) {
				trabajador = new TTrabajador(r.getString("NOMBRE"), r.getString("MAIL"), r.getString("DNI"));
				trabajador.setId(r.getInt("ID_TRABAJADOR"));
				trabajador.setActivo(r.getBoolean("ACTIVO"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return trabajador;
	}

	@Override
	public TTrabajador buscarDatosTrabajadorDNI(String dni) {
		TTrabajador trabajador = null;

		try {
			Transaction t = null;
			Connection connection;
			String slctforUpdate = "";

			try {
				t = TransactionManager.getInstance().getTransaccion();
				connection = (Connection) t.getResource();
				slctforUpdate = " FOR UPDATE";

			} catch (Exception e) {
				connection = DataSourceSingleton.getInstancia().getNewConnection();
			}

			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM TRABAJADORES WHERE DNI = '" + dni + "'" + slctforUpdate);
			r.next();
			trabajador = new TTrabajador(r.getString("NOMBRE"), r.getString("MAIL"), r.getString("DNI"));
			trabajador.setId(r.getInt("ID_TRABAJADOR"));
			trabajador.setActivo(r.getBoolean("ACTIVO"));
		} catch (Exception e) {
			return null;
		}

		return trabajador;
	}

	@Override
	public int reactivar(int id) {
		int res;

		try {
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.getTransaccion();
			Connection connection = (Connection) transaccion.getResource();

			Statement s = connection.createStatement();

			res = s.executeUpdate("UPDATE TRABAJADORES SET ACTIVO = 1 WHERE ID_TRABAJADOR =  " + id + ";");

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		return res;
	}

	
}