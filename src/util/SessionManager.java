package util;

import model.Employee;

public class SessionManager {
	private static Employee user;
	
	public static void login(Employee user) {
		SessionManager.user = user;
	}
	
	public static void logout() {
		SessionManager.user = null;
	}
	
	public static boolean isLoggedIn() {
		return user != null;
	}
	
	public static Employee getUser() {
		return user;
	}
}
