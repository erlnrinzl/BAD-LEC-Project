package app;

import controller.ExampleController;
import controller.HomeController;
import controller.LeaveRequestController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Employee;
import util.RouteManager;
import view.LoginPage;
import view.RegisterPage;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		RouteManager.init(primaryStage);
		RouteManager.addRoute("login", () -> new LoginPage(), "Leave Request System | Login");
		RouteManager.addRoute("register", () -> new RegisterPage(), "Leave Request System | Register");
		RouteManager.addRoute("home", () -> HomeController.index(), "Leave Request System | Home");
		RouteManager.addRoute("employee-leave-request", () -> LeaveRequestController.create(), "Leave Request System | Create New Leave Request");
		RouteManager.addRoute("example", () -> ExampleController.index(), "Leave Request System | Home");
		RouteManager.addRoute("example-create", () -> ExampleController.create(), "Leave Request System | Home");
		RouteManager.addRoute("example-edit", (Employee employee) -> ExampleController.edit(employee), "Leave Request System | Example Home");
//		RouteManager.addRoute("employee-leave-history", new HomePage(), "Leave Request System | Home");
		
		try {
			RouteManager.navigate("login");
		} catch (Exception e) {
			RouteManager.navigate("login");
		}
		primaryStage.show();
	}

}
