package controller;

import util.SessionManager;
import view.Page;

abstract public class HomeController {
	public static Page index() {
		if (!SessionManager.getUser().getPostion().equals("Department Head")) {
			return new view.employee.Home();
		} else {
			return new view.manager.Home();
		}
	}
}
