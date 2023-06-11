package Integración.Cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Integración.DataSource.DataSourceSingleton;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TClienteNoPremium;
import Negocio.Cliente.TClientePremium;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;

public class DAOClienteImp implements DAOCliente {

	@Override

	public int altaCliente(TCliente cliente) {

		TransactionManager tManager = TransactionManager.getInstance();
		int exito = -1;
		int idCliente = -1;
		try {
			Transaction t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			Statement s = c.createStatement();
			exito = s.executeUpdate(
					"INSERT INTO CLIENTES (NOMBRE, DNI, MAIL, ACTIVO) VALUES ('" + cliente.getNombre() + "', '"
							+ cliente.getDNI() + "', '" + cliente.getMail() + "', '1');",
					Statement.RETURN_GENERATED_KEYS);

			ResultSet idInsertado = s.getGeneratedKeys();
			if (idInsertado.next()) {
				idCliente = idInsertado.getInt(1);
				if (cliente instanceof TClientePremium) {
					TClientePremium clienteP = (TClientePremium) cliente;
					exito = s.executeUpdate(
							"INSERT INTO CLIENTE_PREMIUM (ID_SOCIO, ANTIGUEDAD,TELEFONO,DIRECCION) VALUES (" + idCliente
									+ ",'" + clienteP.getAntiguedad().toString() + "'," + clienteP.getTelefono() + ",'"
									+ clienteP.getDireccion() + "');");
				} else if (cliente instanceof TClienteNoPremium) {
					exito = s.executeUpdate("INSERT INTO CLIENTE_NO_PREMIUM (ID_SOCIO, N_COMPRAS) VALUES (" + idCliente
							+ "," + "'0');");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (idCliente != -1 && exito != -1)
			return idCliente;
		else
			return -1;

	}

	@Override
	public int bajaCliente(int id) {
		TransactionManager tManager = TransactionManager.getInstance();
		int exito = -1;
		try {
			Transaction t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			Statement s = c.createStatement();
			String slctforUpdate = " FOR UPDATE";
			exito = s.executeUpdate("UPDATE CLIENTES SET ACTIVO = 0 WHERE ID_SOCIO ='" + id + "';");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	@Override
	public int modificarCliente(TCliente cliente) {
		int exito;
		try {
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.getTransaccion();
			Connection connection = (Connection) transaccion.getResource();
			Statement s = connection.createStatement();

			exito = s.executeUpdate(
					"UPDATE CLIENTES SET NOMBRE = '" + cliente.getNombre() + "', DNI = '" + cliente.getDNI()
							+ "', MAIL ='" + cliente.getMail() + "' WHERE ID_SOCIO = " + cliente.getIDSocio() + ";");

			if (cliente instanceof TClientePremium) {
				TClientePremium clienteP = (TClientePremium) cliente;
				exito = s.executeUpdate("UPDATE CLIENTE_PREMIUM SET TELEFONO = '" + clienteP.getTelefono()
						+ "', DIRECCION = '" + clienteP.getDireccion() + "', ANTIGUEDAD = '" + clienteP.getAntiguedad()
						+ "' WHERE ID_SOCIO = " + clienteP.getIDSocio() + ";");
			} else if (cliente instanceof TClienteNoPremium) {
				TClienteNoPremium clienteNP = (TClienteNoPremium) cliente;
				exito = s.executeUpdate("UPDATE CLIENTE_NO_PREMIUM SET N_COMPRAS = '" + clienteNP.getNumCompras()
						+ "' WHERE ID_SOCIO = " + clienteNP.getIDSocio() + ";");
			}
		} catch (Exception e) {
			exito = -1;
			e.printStackTrace();
		}

		return exito;
	}

	
	public TCliente buscarUnCliente(String id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	
	public TCliente buscarClientePorDNI(String dni) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	@Override
	public TCliente buscarUnCliente(int id) {
		TCliente cliente = null;
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

			resultSet = s.executeQuery(
					"SELECT C.ID_SOCIO, C.NOMBRE, C.DNI, C.MAIL, C.ACTIVO, CP.ANTIGUEDAD, CP.TELEFONO, CP.DIRECCION"
							+ " FROM CLIENTES C, CLIENTE_PREMIUM CP "
							+ "WHERE C.ID_SOCIO = CP.ID_SOCIO AND C.ID_SOCIO = " + id + slctforUpdate + ";");
			if (resultSet != null && resultSet.first()) {
				TClientePremium clienteP = null;
				clienteP = new TClientePremium(resultSet.getInt("ID_SOCIO"));
				clienteP.setNombre(resultSet.getString("NOMBRE"));
				clienteP.setDNI(resultSet.getString("DNI"));
				clienteP.setMail(resultSet.getString("MAIL"));
				clienteP.setEsActivo(resultSet.getBoolean("ACTIVO"));
				String fecha = resultSet.getString("ANTIGUEDAD");
				LocalDate date = null;
				date = date.parse(fecha);
				clienteP.setAntiguedad(date);
				clienteP.setTelefono(resultSet.getString("TELEFONO"));
				clienteP.setDireccion(resultSet.getString("DIRECCION"));
				cliente = clienteP;
			} else {
				resultSet = s.executeQuery("SELECT C.ID_SOCIO, C.NOMBRE, C.DNI, C.MAIL, C.ACTIVO, CNP.N_COMPRAS"
						+ " FROM CLIENTES C, CLIENTE_NO_PREMIUM CNP "
						+ "WHERE C.ID_SOCIO = CNP.ID_SOCIO AND C.ID_SOCIO = " + id + slctforUpdate + ";");
				if (resultSet != null && resultSet.first()) {
					TClienteNoPremium clienteNP = null;
					clienteNP = new TClienteNoPremium(resultSet.getInt(1));
					clienteNP.setNombre(resultSet.getString("NOMBRE"));
					clienteNP.setDNI(resultSet.getString("DNI"));
					clienteNP.setMail(resultSet.getString("MAIL"));
					clienteNP.setEsActivo(resultSet.getBoolean("ACTIVO"));
					clienteNP.setNumCompras(resultSet.getInt("N_COMPRAS"));
					cliente = clienteNP;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}

	@Override
	public int buscarUnClientePorDni(String dni) {
		int salida = -1;
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

			resultSet = s.executeQuery(
					"SELECT ID_SOCIO FROM CLIENTES WHERE CLIENTES.DNI = '" + dni + "'" + slctforUpdate + ";");
			if (resultSet != null && resultSet.first())
				salida = resultSet.getInt("ID_SOCIO");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	@Override
	public List<TCliente> buscarTodosCliente() {
		List<TCliente> clientes = null;
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

			resultSet = s.executeQuery(
					"SELECT * FROM CLIENTE_PREMIUM JOIN CLIENTES ON CLIENTES.ID_SOCIO = CLIENTE_PREMIUM.ID_SOCIO WHERE ACTIVO = 1"
							+ slctforUpdate + ";");
			clientes = new ArrayList<TCliente>();
			while (resultSet.next()) {
				TClientePremium clienteP = new TClientePremium(resultSet.getInt("ID_SOCIO"));
				clienteP.setNombre(resultSet.getString("NOMBRE"));
				clienteP.setDNI(resultSet.getString("DNI"));
				clienteP.setMail(resultSet.getString("MAIL"));
				clienteP.setEsActivo(resultSet.getBoolean("ACTIVO"));
				String fecha = resultSet.getString("ANTIGUEDAD");
				LocalDate date = null;
				date = date.parse(fecha);
				clienteP.setAntiguedad(date);
				clienteP.setTelefono(resultSet.getString("TELEFONO"));
				clienteP.setDireccion(resultSet.getString("DIRECCION"));
				clientes.add(clienteP);
			}
			resultSet = s.executeQuery(
					"SELECT * FROM CLIENTE_NO_PREMIUM JOIN CLIENTES ON CLIENTES.ID_SOCIO = CLIENTE_NO_PREMIUM.ID_SOCIO WHERE ACTIVO = 1"
							+ slctforUpdate + ";");
			while (resultSet.next()) {
				TClienteNoPremium clienteNP = new TClienteNoPremium(resultSet.getInt("ID_SOCIO"));
				clienteNP.setNombre(resultSet.getString("NOMBRE"));
				clienteNP.setDNI(resultSet.getString("DNI"));
				clienteNP.setMail(resultSet.getString("MAIL"));
				clienteNP.setEsActivo(resultSet.getBoolean("ACTIVO"));
				clienteNP.setNumCompras(resultSet.getInt("N_COMPRAS"));
				clientes.add(clienteNP);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return clientes;
	}

	@Override
	public int reactivar(int id_cliente) {
		TransactionManager tManager = TransactionManager.getInstance();
		int exito = -1;
		try {
			Transaction t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			Statement s = c.createStatement();
			exito = s.executeUpdate("UPDATE CLIENTES SET ACTIVO = 1 WHERE CLIENTES.ID_SOCIO = " + id_cliente + ";");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

}