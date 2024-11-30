package app;

import javafx.application.Application;
import javafx.stage.Stage;
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
		RouteManager.addRoute("login", new LoginPage(), "Leave System | Login");
		RouteManager.addRoute("register", new RegisterPage(), "Leave System | Register");
		
		try {
			RouteManager.navigate("home");
		} catch (Exception e) {
			RouteManager.navigate("login");
		}
		primaryStage.show();
	}

}
