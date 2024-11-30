package view;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import util.RouteManager;
import util.SessionManager;

abstract public class AppShell {
	private static MenuBar menuBar = new MenuBar();
	
	public static Scene render(Pane childPane) {
		clearMenus();
		
		if (SessionManager.isLoggedIn()) {
//			if (SessionManager.getUser().getRole() != "User") {
//				renderAdminMenu();
//			} else {
//				renderCustomerMenu();
//			}
		} else {
			renderGuestMenu();
		}
		
		BorderPane rootLayout = new BorderPane();
		rootLayout.setTop(menuBar);
		rootLayout.setCenter(childPane);
		
		return new Scene(rootLayout, 800, 600);
	}
	
	private static void renderCustomerMenu() {
		Menu navMenu = new Menu("Dashboard");
		Menu logoutMenu = new Menu("Logout");
		MenuItem logoutSubMenu = new MenuItem("Logout");
		MenuItem homeSubMenu = new MenuItem("Home");
		MenuItem cartSubMenu = new MenuItem("Cart");
		
		logoutSubMenu.setOnAction(e->{
			//do something, use loginController
		});
		
		if (RouteManager.getCurrentRoute().equals("home")) {
			homeSubMenu.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");
			homeSubMenu.setDisable(true);
			cartSubMenu.setStyle("-fx-font-weight: 100");
			cartSubMenu.setOnAction(e->{
				RouteManager.navigate("cart");
			});
		} else if (RouteManager.getCurrentRoute().equals("cart")) {
			cartSubMenu.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");
			cartSubMenu.setDisable(true);
			homeSubMenu.setStyle("-fx-font-weight: 100");
			homeSubMenu.setOnAction(e->{
				RouteManager.navigate("home");
			});
		}
		
		navMenu.getItems().addAll(homeSubMenu, cartSubMenu);
		logoutMenu.getItems().add(logoutSubMenu);		
		menuBar.getMenus().addAll(navMenu, logoutMenu);
	}
	
	private static void renderAdminMenu() {
		Menu logoutMenu = new Menu("Logout");
		MenuItem logoutSubMenu = new MenuItem("Logout");
		
		logoutMenu.getItems().add(logoutSubMenu);
		menuBar.getMenus().add(logoutMenu);
		
		logoutSubMenu.setOnAction(e->{
			//do something, use loginController
		});
	}
	
	private static void renderGuestMenu() {
		Menu accountMenu = new Menu("Menu");
		MenuItem loginSubMenu = new MenuItem("Login");
		MenuItem registerSubMenu = new MenuItem("Register");
		
		if (RouteManager.getCurrentRoute().equals("login")) {
			loginSubMenu.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");
			loginSubMenu.setDisable(true);
			registerSubMenu.setStyle("-fx-font-weight: 100");
			registerSubMenu.setOnAction(e->{
				RouteManager.navigate("register");
			});
		} else if (RouteManager.getCurrentRoute().equals("register")) {
			registerSubMenu.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");
			registerSubMenu.setDisable(true);
			loginSubMenu.setStyle("-fx-font-weight: 100");
			loginSubMenu.setOnAction(e->{
				RouteManager.navigate("login");
			});
		}
		
		accountMenu.getItems().addAll(loginSubMenu, registerSubMenu);
		menuBar.getMenus().add(accountMenu);
	}
	
	private static void clearMenus() {
	    menuBar.getMenus().clear();
	}
}
