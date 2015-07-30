package ru.agilecamp.habitator.utils;

import ru.agilecamp.habitator.User;

public class UserUtils {

	public static User getUser(String login, String password) {
		User user = new User();
		user.setName(login);
		user.setPassword(password);
		return user;
	}

}
