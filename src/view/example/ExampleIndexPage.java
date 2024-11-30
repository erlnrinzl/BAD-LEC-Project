package view.example;

import controller.ExampleController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Employee;
import util.RouteManager;
import view.Page;

public class ExampleIndexPage extends Page{

    private Label titleLabel;
    private TableView<Employee> employeeTable;
    private Button addBtn, editBtn, deleteBtn;
    private Employee selectedEmployee;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		ExampleController.loadEmployees();
		selectedEmployee = null;

		titleLabel = new Label("All Employee");
		titleLabel.setFont(new Font("Arial Black", 36));
		employeeTable = new TableView<>();
        setupEmployeeTable();
		addBtn = new Button("Add New");
		editBtn = new Button("Edit");
		deleteBtn = new Button("Delete");
	}

	@Override
	public Pane layout() {
		// TODO Auto-generated method stub
		FlowPane btnLayout = new FlowPane();
		btnLayout.getChildren().addAll(addBtn, editBtn, deleteBtn);
		btnLayout.setHgap(10);
		
        VBox homeLayout = new VBox();
        homeLayout.getChildren().addAll(titleLabel, btnLayout, employeeTable);
        homeLayout.setPadding(new Insets(50));
        homeLayout.setSpacing(10);
        homeLayout.setAlignment(Pos.CENTER);

        return homeLayout;
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		employeeTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Employee>() {

			@Override
			public void changed(ObservableValue<? extends Employee> observable, Employee oldVal, Employee newVal) {
				// TODO Auto-generated method stub
				if (newVal != null) {
					ExampleIndexPage.this.selectedEmployee = newVal;
				}
			}
		});
		
		addBtn.setOnMouseClicked(e->{
			RouteManager.navigate("example-create");
		});
		
		editBtn.setOnMouseClicked(e->{
			if (this.selectedEmployee != null) {
				RouteManager.navigate("example-edit", this.selectedEmployee);
				this.selectedEmployee = null;
			} else {
				Alert editErrorAlert = new Alert(AlertType.ERROR);
				editErrorAlert.setContentText("No selected employee!");
				editErrorAlert.show();
			}
		});
		
		deleteBtn.setOnAction(e->{
			if (this.selectedEmployee != null) {
				Alert deleteConfirmAlert = new Alert(AlertType.CONFIRMATION);
				deleteConfirmAlert.setContentText("Are you sure you want to delete " + this.selectedEmployee.getName() + " ?");
				deleteConfirmAlert.showAndWait();
				ExampleController.destroy(this.selectedEmployee);
				this.selectedEmployee = null;
			} else {
				Alert editErrorAlert = new Alert(AlertType.ERROR);
				editErrorAlert.setContentText("No selected employee!");
				editErrorAlert.show();
			}
		});
	}
	
	private void setupEmployeeTable() {
		// TODO Auto-generated method stub
        TableColumn<Employee, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));

        TableColumn<Employee, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmail()));

        TableColumn<Employee, String> dobCol = new TableColumn<>("Date of Birth");
        dobCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDateOfBirth()));
        
        TableColumn<Employee, String> departmenCol = new TableColumn<>("Department");
        departmenCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDepartment().getName()));
        
        TableColumn<Employee, String> positionCol = new TableColumn<>("Position");
        positionCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPostion()));
        
        employeeTable.getColumns().addAll(nameCol, emailCol, dobCol, departmenCol, positionCol);
        employeeTable.setItems(ExampleController.getEmployees());
	}

}
