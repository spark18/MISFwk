package com.shizhong.web.session;

import java.util.HashMap;
import java.util.Map;

import com.shizhong.db.dao.User;

public class UserPool {
	private static final Map<String, User> users = new HashMap<String, User>();

	public static void addUser(User user) {
		users.put(user.getName(), user);
	}

	public static void removeUser(User user) {
		if (null == user) {
			return;
		}
		users.remove(user.getName());
	}
	
	public static User getUser(String name) {
		return users.get(name);
	}
	
	public static boolean isUserLive(String name) {
		return users.get(name) != null;
	}
}
