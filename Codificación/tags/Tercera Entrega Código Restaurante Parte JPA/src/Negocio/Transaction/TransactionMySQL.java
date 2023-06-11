package Negocio.Transaction;

import java.sql.Connection;

import Integración.DataSource.DataSourceSingleton;

public class TransactionMySQL implements Transaction {

	private Connection connection;

	public TransactionMySQL() throws Exception {
		try {
			connection = DataSourceSingleton.getInstancia().getNewConnection();
		} catch (Exception e) {
			throw e;
		}
	}

	public void start() throws Exception {
		connection.setAutoCommit(false);
	}

	public void commit() throws Exception {
		connection.commit();
		connection.close();
		TransactionManager t = TransactionManager.getInstance();
		t.eliminaTransaccion();
	}

	public void rollback() throws Exception {
		connection.rollback();
		connection.close();
		TransactionManager t = TransactionManager.getInstance();
		t.eliminaTransaccion();
	}

	public Object getResource() {
		return connection;

	}
}
