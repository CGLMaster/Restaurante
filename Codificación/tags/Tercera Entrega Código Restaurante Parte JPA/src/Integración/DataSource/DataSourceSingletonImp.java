package Integración.DataSource;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.sql.PooledConnection;

public class DataSourceSingletonImp extends DataSourceSingleton {
	
	private MysqlConnectionPoolDataSource mysqlConnectionPoolDataSource;
	private MysqlConnectionPoolDataSource dataSource;

	public DataSourceSingletonImp() {
		dataSource = new MysqlConnectionPoolDataSource();
		dataSource.setServerName("35.233.32.45");
		dataSource.setPort(3306);
		dataSource.setDatabaseName("msbd");
		dataSource.setUser("IBMUser");
		dataSource.setPassword("Javajavita69");
	}

	public Connection getNewConnection() throws SQLException {
		PooledConnection pooledConnection = dataSource.getPooledConnection();
		Connection connToMySQL = pooledConnection.getConnection();

		return connToMySQL;
	}

}