package controller;

import util.SessionManager;
import view.Page;
import view.employee.FormLeaveRequestPage;

abstract public class LeaveRequestController {
	public static Page create() {
		return new FormLeaveRequestPage();
	}
	
	public static void store() {
		//store method to database
	}
	
}
