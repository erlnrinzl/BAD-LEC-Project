package util;

import model.Employee;

public class SessionManager {
	private static Employee user;
	
	public static void create(Employee user) {
		SessionManager.user = user;
	}
	
	public static void destroy() {
		SessionManager.user = null;
	}
	
	public static boolean isLoggedIn() {
		return user != null;
	}
	
	public static Employee getUser() {
		return user;
	}
}
