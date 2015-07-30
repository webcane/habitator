package ru.agilecamp.habitator.db.derby;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import ru.agilecamp.habitator.db.DaoFactory;
import ru.agilecamp.habitator.db.UserDao;

public class DerbyDaoFactory implements DaoFactory {

	private DataSource dataSource;
	
	/**
	 * Constructor
	 * 
	 * @param dataSource
	 */
	public DerbyDaoFactory(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Connection getConnection() {
		Connection connection = null;
		if(dataSource != null) {
			try {
			connection = dataSource.getConnection();
			} catch(Exception ex) {
				// TODO log: no connection
			}
		}
		return connection;
	}

	@Override
	public UserDao getUserDao() {
		return new DerbyUserDao(getConnection());
	}

}
