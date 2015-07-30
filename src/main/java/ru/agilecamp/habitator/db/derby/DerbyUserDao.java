package ru.agilecamp.habitator.db.derby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ru.agilecamp.habitator.HabitsException;
import ru.agilecamp.habitator.User;
import ru.agilecamp.habitator.db.UserDao;

public class DerbyUserDao implements UserDao {

	// TODO db scheme name
	private static final String IS_USER_EXISTS_SQL = "SELECT id FROM HABITATOR01.USERS WHERE name = ?";
	private static final String ADD_USER_SQL = "INSERT INTO HABITATOR01.USERS (name, password) VALUES (?, ?)";
	private static final String DROP_USER_SQL = "DELETE FROM HABITATOR01.USERS WHERE id = ?";
	private static final String LOGIN_SQL = "SELECT id FROM HABITATOR01.USERS WHERE name = ? AND password = ?";

	private Connection connection;

	/**
	 * Constructor
	 * 
	 * @param connection
	 *            db connection
	 */
	public DerbyUserDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void createUser(User user) throws SQLException {
		if (connection != null) {
			try {
				PreparedStatement ps = connection.prepareStatement(ADD_USER_SQL);
				ps.setString(1, user.getName());
				ps.setString(2, user.getPassword());
				ps.execute();
			} finally {
				// connection.close();
			}
		}
	}

	@Override
	public void updateUser(User user) throws SQLException {
		// TODO
	}

	@Override
	public User getUser(int userId) throws SQLException {
		// TODO
		return null;
	}

	@Override
	public void deleteUser(int userId) throws SQLException {
		if (connection != null) {
			try {
				PreparedStatement ps = connection.prepareStatement(DROP_USER_SQL);
				ps.setInt(1, userId);
				ps.execute();
			} finally {
				// connection.close();
			}
		}
	}

	@Override
	public User findUserByName(String userName) throws SQLException {
		User user = null;
		try {
			PreparedStatement ps = connection.prepareStatement(IS_USER_EXISTS_SQL);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				// наш юзер не возвращает пароль
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(userName);
			}

		} finally {
			// connection.close();
		}
		return user;
	}

	@Override
	public int findUser(User user) throws SQLException {
		try {
			PreparedStatement ps = connection.prepareStatement(LOGIN_SQL);

			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());

			ResultSet result = ps.executeQuery();
			return result.next() ? result.getInt("id") : -1;

		} finally {
			// connection.close();
		}
	}

}
