package util;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import view.Page;

abstract public class RouteManager {
	private static Stage primaryStage;
	private static final HashMap<String, Route<?>> routes = new HashMap<>();
	private static String currentRoute;
	
	public static void init(Stage stage) {
		primaryStage = stage;
	}
		
	public static <T> void addRoute(String name, Function<T, Page> pageFunction, String title) {
		routes.put(name, new Route<>(pageFunction, title));
	}
	
	public static void addRoute(String name, Supplier<Page> pageSupplier, String title) {
	    routes.put(name, new Route<>(model -> pageSupplier.get(), title));
	}

	
	public static void navigate(String routeName) {
		navigate(routeName, null);
	}
	
	public static <T> void navigate(String routeName, T model) {
        if (!routes.containsKey(routeName)) {
            Alert pageErrorAlert = new Alert(AlertType.ERROR);
            pageErrorAlert.setContentText("404.. page " + routeName + " not found!");
            pageErrorAlert.showAndWait();
            throw new IllegalArgumentException("Route " + routeName + " not found");
        }

        Route<T> route = (Route<T>) routes.get(routeName);
        currentRoute = routeName;

        primaryStage.setScene(route.pageFunction.apply(model).render());
        primaryStage.setTitle(route.title);
    }

	public static String getCurrentRoute() {
		return currentRoute;
	}

	public static void setCurrentRoute(String currentRoute) {
		RouteManager.currentRoute = currentRoute;
	}
	
	private static class Route<T> {
		Function<T, Page> pageFunction;
		String title;
		
		Route(Function<T, Page> pageFunction, String title) {
			this.pageFunction = pageFunction;
			this.title = title;
		}
	}
	
}
