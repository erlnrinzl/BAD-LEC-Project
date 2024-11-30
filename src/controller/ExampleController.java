package controller;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Department;
import model.Employee;
import view.Page;
import view.example.ExampleCreatePage;
import view.example.ExampleEditPage;
import view.example.ExampleIndexPage;

abstract public class ExampleController {
	private static EmployeeDAO employeeDAO = new EmployeeDAO();
	private static DepartmentDAO departmentDAO = new DepartmentDAO();
	private static ObservableList<Employee> employees = FXCollections.observableArrayList();
	private static ObservableList<Department> departments = FXCollections.observableArrayList();
	
	public static ObservableList<Employee> getEmployees() {
		return employees;
	}
	
	public static ObservableList<Department> getDepartments() {
		return departments;
	}

	public static void loadEmployees() {
		employees.clear();
		employees.addAll(employeeDAO.read());
	}
	
	public static void loadDepartments() {
		departments.clear();
		departments.addAll(departmentDAO.read());
	}

	public static Page index() {
		return new ExampleIndexPage();
	}
	
	public static Page create() {
		return new ExampleCreatePage();
	}
	
	public static Employee store(Employee newEmployee) {
		newEmployee.setID(employeeDAO.generateEmployeeID());
		employeeDAO.create(newEmployee);
		return newEmployee;
	}
	
	public static Page edit(Employee employee) {
		return new ExampleEditPage(employee);
	}
	
	public static Employee update(Employee employee) {
		employeeDAO.update(employee);
		return employee;
	}
	
	public static Employee destroy(Employee employee) {
		employees.remove(employee);
		employeeDAO.delete(employee);
		return employee;
	}
}
