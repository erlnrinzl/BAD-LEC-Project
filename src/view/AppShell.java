package view;

import controller.AuthController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
			if (!SessionManager.getUser().getDepartment().getName().equals("HR Department")) {
				if (!SessionManager.getUser().getPostion().equals("Department Head")) {
					renderDepartmentMenu();
				} else {
					renderDepartmentHeadMenu();
				}
			} else {
				renderHRMenu();
			}
		} else {
			renderGuestMenu();
		}
		
		BorderPane rootLayout = new BorderPane();
		rootLayout.setTop(menuBar);
		rootLayout.setCenter(childPane);
		
		return new Scene(rootLayout, 800, 600);
	}
	
	private static void renderDepartmentMenu() {
		menuBar.getMenus().addAll(initDepartmentMenu(), initLogoutMenu());
	}
	
	private static void renderDepartmentHeadMenu () {
		menuBar.getMenus().addAll(initLogoutMenu());
	}
	
	private static void renderHRMenu() {
		menuBar.getMenus().addAll(initLogoutMenu());
	}
	
	private static void renderGuestMenu() {
		Menu accountMenu = new Menu("Menu");
		MenuItem loginSubMenu = new MenuItem("Login");
		MenuItem registerSubMenu = new MenuItem("Register");
		
		if (RouteManager.getCurrentRoute().equals("login")) {
			loginSubMenu.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");
			loginSubMenu.setDisable(true);
			registerSubMenu.setStyle("-fx-font-weight: 100");
		} else if (RouteManager.getCurrentRoute().equals("register")) {
			registerSubMenu.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");
			registerSubMenu.setDisable(true);
			loginSubMenu.setStyle("-fx-font-weight: 100");
		}
		accountMenu.getItems().addAll(loginSubMenu, registerSubMenu);
		menuBar.getMenus().addAll(accountMenu, initExampleMenu());

		registerSubMenu.setOnAction(e->{
			RouteManager.navigate("register");
		});
		
		loginSubMenu.setOnAction(e->{
			RouteManager.navigate("login");
		});
		
	}
	
	private static void clearMenus() {
	    menuBar.getMenus().clear();
	}
	
	private static Menu initLogoutMenu() {
		// init
		Menu logoutMenu = new Menu("Logout");
		MenuItem logoutSubMenu = new MenuItem("Logout");
		logoutMenu.getItems().add(logoutSubMenu);
		
		// set action
		logoutSubMenu.setOnAction(e->{
			Alert logoutAlert = new Alert(AlertType.INFORMATION);
			logoutAlert.setContentText("Loging off...");
			logoutAlert.showAndWait();
			AuthController.logoutAccount();
		});
		
		return logoutMenu;
	}
	
	private static Menu initDepartmentMenu() {
		// init
		Menu navMenu = new Menu("Dashboard");
		Menu logoutMenu = new Menu("Logout");
		MenuItem logoutSubMenu = new MenuItem("Logout");
		MenuItem homeSubMenu = new MenuItem("Home");
		MenuItem createLeaveSubMenu = new MenuItem("Create New Leave Request");
		MenuItem historyLeaveSubMenu = new MenuItem("Employee Leave History");
		initLogoutMenu();
		
		//layout
		if (RouteManager.getCurrentRoute().equals("home")) {
			homeSubMenu.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");
			homeSubMenu.setDisable(true);
			historyLeaveSubMenu.setStyle("-fx-font-weight: 100");
		} else if (RouteManager.getCurrentRoute().equals("employee-leave-request")) {
			createLeaveSubMenu.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");
			createLeaveSubMenu.setDisable(true);
			homeSubMenu.setStyle("-fx-font-weight: 100");
			historyLeaveSubMenu.setStyle("-fx-font-weight: 100");
			historyLeaveSubMenu.setOnAction(e->{
				RouteManager.navigate("employee-leave-history");
			});
		} else if (RouteManager.getCurrentRoute().equals("employee-leave-history")) {
			historyLeaveSubMenu.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");
			historyLeaveSubMenu.setDisable(true);
			homeSubMenu.setStyle("-fx-font-weight: 100");
			homeSubMenu.setOnAction(e->{
				RouteManager.navigate("home");
			});
			createLeaveSubMenu.setStyle("-fx-font-weight: 100");
			createLeaveSubMenu.setOnAction(e->{
				RouteManager.navigate("employee-leave-request");
			});
		}
		navMenu.getItems().addAll(homeSubMenu, createLeaveSubMenu, historyLeaveSubMenu);
		
		// set action
		homeSubMenu.setOnAction(e->{
			RouteManager.navigate("home");
		});
		createLeaveSubMenu.setOnAction(e->{
			RouteManager.navigate("employee-leave-request");
		});
		historyLeaveSubMenu.setOnAction(e->{
			RouteManager.navigate("employee-leave-history");
		});
		
		return navMenu;
	}
	
	private static Menu initExampleMenu() {
		Menu exampleMenu = new Menu("ExampleWorkflows");
		MenuItem homeSubMenu = new MenuItem("Go To Home");
		
		exampleMenu.getItems().add(homeSubMenu);
		
		homeSubMenu.setOnAction(e->{
			RouteManager.navigate("example");
		});
		
		return exampleMenu;
	}
}
