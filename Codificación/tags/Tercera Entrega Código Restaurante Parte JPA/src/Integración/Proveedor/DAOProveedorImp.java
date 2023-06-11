package Integración.Proveedor;

import Negocio.Proveedor.TProveedor;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integración.DataSource.DataSourceSingleton;

public class DAOProveedorImp implements DAOProveedor {

	public int altaProveedor(String nombre) {
		int r;

		try {
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.getTransaccion();
			Connection connection = (Connection) transaccion.getResource();

			Statement s = connection.createStatement();

			r = s.executeUpdate("INSERT INTO PROVEEDOR(NOMBRE, ACTIVO) VALUES('" + nombre + "', " + +1 + ")",
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

	public TProveedor buscarProveedor(int id) {
		TProveedor proveedor = null;
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

			ResultSet r = s.executeQuery("SELECT * FROM PROVEEDOR WHERE ID_PROVEEDOR =" + id + slctforUpdate);

			r.next();
			proveedor = new TProveedor(r.getString("NOMBRE"));
			proveedor.setID(r.getInt("ID_PROVEEDOR"));
			proveedor.setActivo(r.getBoolean("ACTIVO"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return proveedor;
	}

	public TProveedor buscarProveedorPorNombre(String nombre) {
		TProveedor proveedor = null;
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

			ResultSet r = s.executeQuery("SELECT * FROM PROVEEDOR WHERE NOMBRE = '" + nombre + "'" + slctforUpdate);

			r.next();
			proveedor = new TProveedor(r.getString("NOMBRE"));
			proveedor.setID(r.getInt("ID_PROVEEDOR"));
			proveedor.setActivo(r.getBoolean("ACTIVO"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return proveedor;
	}

	public List<TProveedor> buscarTodosProveedor() {
		List<TProveedor> proveedores = null;
		Connection connection = null;
		try {
			Transaction t = null;
			try {
				t = TransactionManager.getInstance().getTransaccion();
				connection = (Connection) t.getResource();
			} catch (Exception e) {
				connection = DataSourceSingleton.getInstancia().getNewConnection();
			}
			Statement s = connection.createStatement();
			ResultSet result = s.executeQuery("SELECT * FROM PROVEEDOR WHERE ACTIVO = 1");
			proveedores = new ArrayList<TProveedor>();

			while (result.next()) {
				TProveedor proveedor = new TProveedor(result.getString("NOMBRE"));
				proveedor.setActivo(result.getBoolean("ACTIVO"));
				proveedor.setID(result.getInt("ID_PROVEEDOR"));
				proveedores.add(proveedor);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return proveedores;
	}

	public int modificarProveedor(TProveedor proveedor) {
		int r;

		try {
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.getTransaccion();
			Connection connection = (Connection) transaccion.getResource();

			Statement s = connection.createStatement();

			r = s.executeUpdate("UPDATE PROVEEDOR SET NOMBRE = '" + proveedor.getNombre() + "' WHERE ID_PROVEEDOR = "
					+ proveedor.getID());

		} catch (Exception e) {
			r = -1;
			e.printStackTrace();
		}

		return r;
	}

	public int eliminarProveedor(int id) {
		int r;

		try {
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.getTransaccion();
			Connection connection = (Connection) transaccion.getResource();

			Statement s = connection.createStatement();
			r = s.executeUpdate("UPDATE PROVEEDOR SET ACTIVO = 0 WHERE ID_PROVEEDOR =  " + id + ";");
		} catch (Exception e) {
			return -1;
		}
		return r;
	}

	public int reactivar(int id) {
		int res;

		try {
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.getTransaccion();
			Connection connection = (Connection) transaccion.getResource();

			Statement s = connection.createStatement();

			res = s.executeUpdate("UPDATE PROVEEDOR SET ACTIVO = 1 WHERE ID_PROVEEDOR =  " + id);

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		return res;
	}
}