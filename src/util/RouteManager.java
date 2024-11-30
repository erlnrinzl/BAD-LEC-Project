package util;

import java.util.HashMap;
import java.util.function.Supplier;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import view.Page;

abstract public class RouteManager {
	private static Stage primaryStage;
	private static final HashMap<String, Route> routes = new HashMap<>();
	private static String currentRoute;
	
	public static void init(Stage stage) {
		primaryStage = stage;
	}
	
	public static void addRoute(String name, Page page, String title) {
		routes.put(name, new Route(page, title));
	}
	
	public static void navigate(String routeName) {
		if (!routes.containsKey(routeName)) {
			Alert pageErrorAlert = new Alert(AlertType.ERROR);
			pageErrorAlert.setContentText("404.. page " + routeName + " not found!");
			pageErrorAlert.showAndWait();
			throw new IllegalArgumentException("Route " + routeName + " not found");
		}
		
		Route route = routes.get(routeName);
		currentRoute = routeName;
		
		primaryStage.setScene(route.page.render());
		primaryStage.setTitle(route.title);
	}

	public static String getCurrentRoute() {
		return currentRoute;
	}

	public static void setCurrentRoute(String currentRoute) {
		RouteManager.currentRoute = currentRoute;
	}
	
	private static class Route {
		Page page;
		String title;
		
		Route(Page page , String title) {
			this.page = page;
			this.title = title;
		}
	}
	
}
