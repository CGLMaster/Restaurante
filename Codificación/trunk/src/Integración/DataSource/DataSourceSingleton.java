package Integración.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public abstract class DataSourceSingleton {
	private static DataSourceSingleton instancia;

	public static DataSourceSingleton getInstancia() {
		if (instancia == null) {
			instancia = new DataSourceSingletonImp();
		}

		return instancia;
	}

	public abstract Connection getNewConnection() throws SQLException;
}