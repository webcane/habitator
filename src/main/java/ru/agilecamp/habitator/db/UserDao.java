package ru.agilecamp.habitator.db;

import java.sql.SQLException;

import ru.agilecamp.habitator.User;

/**
 * Интерфейс для управления персистентным состоянием объектов типа User.
 * 
 * Active Record (Активная запись)
 * 
 * @author cane
 */
public interface UserDao {

	public void createUser(User user) throws SQLException;

	public void updateUser(User user) throws SQLException;

	public User getUser(int userId) throws SQLException;

	public void deleteUser(int userId) throws SQLException;

	public int findUser(User user) throws SQLException;

	public User findUserByName(String userName) throws SQLException;
}
