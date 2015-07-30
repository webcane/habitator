package ru.agilecamp.habitator.db;

import java.sql.Connection;
import java.sql.SQLException;

 
/**
 * Фабрика dao.

 * @author cane
 */
public interface DaoFactory {

	/**
	 * Возвращает подключение к базе данных
	 * @return Connection
	 * @throws SQLException
	 */
	public Connection getConnection();
	
	/**
	 * @param con db connection
	 * @return вернет текущую реализацию UserDao 
	 */
	public UserDao getUserDao();

}
