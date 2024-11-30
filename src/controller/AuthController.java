package controller;

import dao.EmployeeDAO;
import model.Employee;
import util.RouteManager;
import util.SessionManager;

abstract public class AuthController {
	static EmployeeDAO employeeDAO = new EmployeeDAO();
	
	public static Employee authenticate(String email, String password) {
		if (employeeDAO.isValidPassword(email, password)) {
			Employee authenticatedEmployee = employeeDAO.getEmployeeByEmail(email);
			SessionManager.create(authenticatedEmployee);
			return authenticatedEmployee;
		}
		return null;
	}
	
	public static void logoutAccount() {
		SessionManager.destroy();
		RouteManager.navigate("login");
	}

}
