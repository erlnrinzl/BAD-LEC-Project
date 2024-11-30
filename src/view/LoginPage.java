package view;

import controller.AuthController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.RouteManager;

public class LoginPage extends Page {
	
	private Label loginLabel;
	private Label emailLabel;
	private Label passLabel;
	private Label registerLabel;
	private Label registerRouteLabel;
	private TextField emailField;
	private PasswordField passField;
	private Button loginBtn;
	
	public LoginPage() {
		super();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub		
		loginLabel = new Label("LOGIN");
		loginLabel.setFont(new Font("Arial Black", 36));
		emailLabel = new Label("Email");
		passLabel = new Label("Password");
		registerLabel = new Label("Don't have an account?");
		registerRouteLabel = new Label("Sign Up!");
		registerRouteLabel.setTextFill(Color.DARKBLUE);
		emailField = new TextField();
		passField= new PasswordField();
		loginBtn = new Button("Login");
	}

	@Override
	public Pane layout() {
		// TODO Auto-generated method stub
		GridPane gridLayout = new GridPane();
		gridLayout.add(emailLabel, 0, 0);
		gridLayout.add(emailField, 1, 0);
		gridLayout.add(passLabel, 0, 1);
		gridLayout.add(passField, 1, 1);
		gridLayout.setAlignment(Pos.CENTER);
		gridLayout.setPadding(new Insets(10));
		gridLayout.setHgap(20);
		gridLayout.setVgap(10);
		
		FlowPane fpLayout = new FlowPane();
		fpLayout.getChildren().addAll(registerLabel, registerRouteLabel);
		fpLayout.setAlignment(Pos.CENTER);
		fpLayout.setPadding(new Insets(10));
		fpLayout.setHgap(10);
		
		VBox vLayout = new VBox();
	    vLayout.getChildren().add(loginLabel);
	    vLayout.getChildren().add(gridLayout);
	    vLayout.getChildren().add(loginBtn);
	    vLayout.getChildren().add(fpLayout);
		vLayout.setAlignment(Pos.CENTER);
		vLayout.setPadding(new Insets(50));
		vLayout.setSpacing(10);
	
		return vLayout;
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		registerRouteLabel.setOnMouseClicked(e->{
			RouteManager.navigate("register");
		});
		
		loginBtn.setOnMouseClicked(e->{
			String email = emailField.getText();
			String password = passField.getText();
			
			if (AuthController.authenticate(email, password) != null) {
				RouteManager.navigate("home");
			} else {
				Alert loginErrorAlert = new Alert(AlertType.ERROR);
				loginErrorAlert.setContentText("Your email or password is incorrect!");
				loginErrorAlert.show();
			}
		});
	}

}
