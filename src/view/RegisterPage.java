package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.RouteManager;

public class RegisterPage extends Page {
	private Label registerLabel;
	private Label usernameLabel;
	private Label emailLabel;
	private Label passLabel;
	private Label confirmPassLabel;
	private Label ageLabel;
	private Label genderLabel;
	private Label countryLabel;
	private Label phoneLabel;
	private Label termsLabel;
	private Label loginLabel;
	private Label loginRouteLabel;
	private TextField usernameField;
	private TextField emailField;
	private PasswordField passField;
	private PasswordField confirmPassField;
	private TextField phoneField;
	private RadioButton maleRadio;
	private RadioButton femaleRadio;
	private ChoiceBox<String> countryChoiceBox;
	private Spinner<Integer> ageSpinner;
	private CheckBox termsCheckbox;
	private Button registerBtn;
	
	
	public RegisterPage() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		registerLabel = new Label("REGISTER");
		registerLabel.setFont(new Font("Arial Black", 36));
		usernameLabel = new Label("Username");
		emailLabel = new Label("Email");
		passLabel = new Label("Password");
		confirmPassLabel = new Label("Confirm Password");
		ageLabel = new Label("Age");
		genderLabel = new Label("Gender");
		countryLabel = new Label("Country");
		phoneLabel = new Label("Phone Number");
		termsLabel = new Label("Agree to terms and condition");
		loginLabel = new Label("Already have an account?");
		loginRouteLabel = new Label("Sign In!");
		loginRouteLabel.setTextFill(Color.DARKBLUE);
		
		usernameField = new TextField();
		usernameField.setPromptText("Username");
		emailField = new TextField();
		emailField.setPromptText("Email");
		passField= new PasswordField();
		passField.setPromptText("Password");
		confirmPassField= new PasswordField();
		confirmPassField.setPromptText("Confirm Password");
		phoneField = new TextField();
		phoneField.setPromptText("Phone Number");
		
		maleRadio = new RadioButton("Male");
		femaleRadio = new RadioButton("Female");
		ToggleGroup genderGroup = new ToggleGroup();
		maleRadio.setToggleGroup(genderGroup);
		maleRadio.setSelected(true);
		femaleRadio.setToggleGroup(genderGroup);
		
		countryChoiceBox = new ChoiceBox<>();
		countryChoiceBox.getItems().addAll("Indonesia", "Malaysia", "Singapore", "Thailand");
		countryChoiceBox.getSelectionModel().selectFirst();
		
		ageSpinner = new Spinner<>(1, 100, 18);
        ageSpinner.setEditable(true);
        
        termsCheckbox = new CheckBox();
        
        registerBtn = new Button("Register");
	}

	@Override
	public Pane layout() {
		// TODO Auto-generated method stub
        FlowPane flowGenderLayout = new FlowPane();
        flowGenderLayout.getChildren().addAll(maleRadio, femaleRadio);
        flowGenderLayout.setHgap(10);
        
        GridPane formLayout = new GridPane();
        formLayout.add(usernameLabel, 0, 0);
        formLayout.add(usernameField, 1, 0);
        formLayout.add(emailLabel, 0, 1);
        formLayout.add(emailField, 1, 1);
        formLayout.add(passLabel, 0, 2);
        formLayout.add(passField, 1, 2);
        formLayout.add(confirmPassLabel, 0, 3);
        formLayout.add(confirmPassField, 1, 3);
        formLayout.add(ageLabel, 0, 4);
        formLayout.add(ageSpinner, 1, 4);
        formLayout.add(genderLabel, 0, 5);
        formLayout.add(flowGenderLayout, 1, 5);
        formLayout.add(countryLabel, 0, 6);
        formLayout.add(countryChoiceBox, 1, 6);
        formLayout.add(phoneLabel, 0, 7);
        formLayout.add(phoneField, 1, 7);
        formLayout.setVgap(10);
        formLayout.setHgap(20);
        formLayout.setAlignment(Pos.CENTER);
        
        FlowPane termsLayout = new FlowPane();
        termsLayout.getChildren().addAll(termsCheckbox, termsLabel);
        termsLayout.setHgap(10);
        termsLayout.setAlignment(Pos.CENTER);
        
        FlowPane loginRouteLayout = new FlowPane();
        loginRouteLayout.getChildren().addAll(loginLabel, loginRouteLabel);
        loginRouteLayout.setHgap(10);
        loginRouteLayout.setAlignment(Pos.CENTER);
        
        VBox vLayout = new VBox();
        vLayout.getChildren().add(registerLabel);
        vLayout.getChildren().add(formLayout);
        vLayout.getChildren().add(termsLayout);
        vLayout.getChildren().add(registerBtn);
        vLayout.getChildren().add(loginRouteLayout);
		vLayout.setAlignment(Pos.CENTER);
		vLayout.setPadding(new Insets(50));
		vLayout.setSpacing(10);
		
		return vLayout;
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		loginRouteLabel.setOnMouseClicked(e->{
			RouteManager.navigate("login");
		});
		
        registerBtn.setOnAction(e->{
        	//do something here
        });
        
		countryChoiceBox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldVal, String newVal) {
				// TODO Auto-generated method stub
				System.out.println(observable);
				System.out.println(oldVal);
				System.out.println(newVal);
			}
		});
	}

}
