package com.virtualspacex.util.database.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import com.virtualspacex.util.database.DatabaseUtilInterface;


/**
 * Hibernateツールクラス
 *
 * @version 0.0.1
 * @since JDK8
 */
public class JdbcUtils implements DatabaseUtilInterface<Statement, Connection>{

	private final String driver;
	private final String url;
	private final String username;
	private final String password;
    
    public JdbcUtils(String configFilename) throws Exception {
    	InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream(configFilename);
		Properties prop = new Properties();
		prop.load(in);
		
		driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		username = prop.getProperty("username");
		password = prop.getProperty("password");
		
		if(null != driver && !("".equals(driver))) {
			Class.forName(driver);
		}
		
    }

	@Override
	public Statement connect() throws Exception {
        return DriverManager.getConnection(url, username, password).createStatement();
	}

	@Override
	public void disConnect(Statement s) throws Exception {
        if (s != null && !(s.isClosed())) {
        	s.close();
        }
	}

	@Override
	public Connection transaction(Statement c) throws Exception {
		c.getConnection().setAutoCommit(false);
		return c.getConnection();
	}

	@Override
	public void commit(Connection t) throws Exception {
		t.commit();
		t.setAutoCommit(true);
	}

	@Override
	public void rollback(Connection t) throws Exception {
		t.rollback();
		t.setAutoCommit(true);
	}

	@Override
	public Class<Statement> getExecutorType() {
		return Statement.class;
	}

	@Override
	public Class<Connection> getTransactionType() {
		return Connection.class;
	}
}
