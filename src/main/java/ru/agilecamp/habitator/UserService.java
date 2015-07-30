package ru.agilecamp.habitator;

import java.sql.SQLException;

import javax.sql.DataSource;

import ru.agilecamp.habitator.db.UserDao;
import ru.agilecamp.habitator.db.derby.DerbyDaoFactory;
import ru.agilecamp.habitator.utils.UserUtils;

/**
 *
 * @author d_alex
 */
public class UserService {

	private UserDao userDao = null;

	public UserService(DataSource dataSource) {
		userDao = new DerbyDaoFactory(dataSource).getUserDao();
	}

	/**
	 * Register new user
	 *
	 * @param login
	 *            - user login
	 * @param password
	 *            - user password
	 * @param passwordAgain
	 *            - user password again
	 * @throws HabitsException
	 */
	public void registerUser(String login, String password, String passwordAgain) throws HabitsException, SQLException {

		if (login.length() > 0 && password.length() > 0 && password.equals(passwordAgain)) {
			User user = userDao.findUserByName(login);
			if (user != null) {
				throw new HabitsException("user already exists");
			}

			else {
				userDao.createUser(UserUtils.getUser(login, password));
			}
		} else {
			throw new HabitsException("Password not equals another password or login is empty");
		}
	}

	/**
	 * authenticate user
	 *
	 * @param login
	 * @param password
	 * @return user id
	 */
	public Integer authentication(String login, String password) throws SQLException {
		int userId = userDao.findUser(UserUtils.getUser(login, password)); 
		return userId != -1 ? userId : null;
	}

	/**
	 * Drop user
	 *
	 * @param id
	 *            - user id
	 */
	public void dropUser(int userId) throws SQLException {
		userDao.deleteUser(userId);
	}
}
