package view.example;

import java.time.LocalDate;

import controller.ExampleController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import model.Department;
import model.Employee;
import view.Page;

public class ExampleCreatePage extends Page {
	private TextField nameField, emailField;
	private PasswordField passField;
	private Button submitBtn;
	private ChoiceBox<Department> departmentChoiceBox;
	private ChoiceBox<String> positionChoiceBox;
	private DatePicker dobPicker;
	private Department selectedDepartment;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		ExampleController.loadDepartments();
		
		nameField = new TextField();
		emailField = new TextField();
		passField = new PasswordField();
		submitBtn = new Button("Submit");
		
		departmentChoiceBox = new ChoiceBox<>();
		departmentChoiceBox.getItems().addAll(ExampleController.getDepartments());
		departmentChoiceBox.setConverter(new StringConverter<Department>() {
			
			@Override
			public String toString(Department department) {
				// TODO Auto-generated method stub
				return department.getName();
			}
			
			@Override
			public Department fromString(String string) {
				// TODO Auto-generated method stub
				return ExampleController.getDepartments().stream()
						.filter(department -> department.getName().equals(string))
						.findFirst()
						.orElse(null);
			}
		});
		departmentChoiceBox.getSelectionModel().select(0);
		this.selectedDepartment = departmentChoiceBox.getValue();
		
		positionChoiceBox = new ChoiceBox<>();
		positionChoiceBox.getItems().addAll("Data Analyst", "Customer Support Representative", "Department Head", "Senior Software Engineer");
		positionChoiceBox.getSelectionModel().selectFirst();
		
		dobPicker = new DatePicker();
		dobPicker.setValue(LocalDate.now());
	}

	@Override
	public Pane layout() {
		// TODO Auto-generated method stub
		Label titleLabel = new Label("Create New Employee");
		titleLabel.setFont(new Font("Arial Black", 36));
		Label nameLabel = new Label("Name");
		Label emailLabel = new Label("Email");
		Label passLabel = new Label("Password");
		Label dobLabel = new Label("Date of Birth");
		Label departmentLabel = new Label("Department");
		Label positionLabel = new Label("Position");
		
        
        GridPane formLayout = new GridPane();
        formLayout.add(nameLabel, 0, 0);
        formLayout.add(nameField, 1, 0);
        formLayout.add(emailLabel, 0, 1);
        formLayout.add(emailField, 1, 1);
        formLayout.add(passLabel, 0, 2);
        formLayout.add(passField, 1, 2);
        formLayout.add(dobLabel, 0, 3);
        formLayout.add(dobPicker, 1, 3);
        formLayout.add(departmentLabel, 0, 4);
        formLayout.add(departmentChoiceBox, 1, 4);
        formLayout.add(positionLabel, 0, 5);
        formLayout.add(positionChoiceBox, 1, 5);
        formLayout.setVgap(10);
        formLayout.setHgap(20);
        formLayout.setAlignment(Pos.CENTER_LEFT);
        
        VBox vLayout = new VBox();
        vLayout.getChildren().add(titleLabel);
        vLayout.getChildren().add(formLayout);
        vLayout.getChildren().add(submitBtn);
        VBox.setMargin(submitBtn, new Insets(20, 0, 20, 0));
		vLayout.setAlignment(Pos.CENTER_LEFT);
		vLayout.setPadding(new Insets(50));
		vLayout.setSpacing(10);
		
		return vLayout;
        
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		departmentChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Department>() {

			@Override
			public void changed(ObservableValue<? extends Department> observableValue, Department oldVal, Department newVal) {
				// TODO Auto-generated method stub
				if (newVal != null) {
					ExampleCreatePage.this.selectedDepartment = newVal;
				}
			}
		});
		
	    submitBtn.setOnAction(e->{
	    		String name = nameField.getText();
	    		String email = emailField.getText();
	    		String password = passField.getText();
	    		String dob = dobPicker.getValue().toString();
	    		String position = positionChoiceBox.getValue();
	    		Department department = this.selectedDepartment;
	    		
	    		if (!(name.isEmpty() || email.isEmpty() || dob.isEmpty() || position.isEmpty() || department == null)) {
	    			try {
		    			Employee newEmployee = new Employee(name, email, dob, position, department.getID());
		    			newEmployee.setPassword(password);
		    			ExampleController.store(newEmployee);
	    			} catch (Exception err) {
	    				// TODO: handle exception
	    				Alert sqlErrorAlert = new Alert(AlertType.ERROR);
	    				sqlErrorAlert.setContentText("Recording Error");
	    				sqlErrorAlert.show();
	    				err.printStackTrace();
	    			}
	    			
	    			departmentChoiceBox.getSelectionModel().select(0);
	    			this.selectedDepartment = departmentChoiceBox.getValue();
	    			nameField.clear();
	    			nameField.requestFocus();
	    			emailField.clear();
	    			passField.clear();
	    			dobPicker.setValue(LocalDate.now());
	    			positionChoiceBox.getSelectionModel().selectFirst();
	    			departmentChoiceBox.getSelectionModel().selectFirst();
	    			
				} else {
		    		Alert formErrorAlert = new Alert(AlertType.ERROR);
		    		formErrorAlert.setContentText("Missing field");
		    		formErrorAlert.show();	    		
				}
	    		
    			Alert formSuccessAlert = new Alert(AlertType.INFORMATION);
    			formSuccessAlert.setContentText("New employee created successfuly!");
    			formSuccessAlert.showAndWait();
	    		
	    });
	}

}
