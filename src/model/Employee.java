package model;

public class Employee {
	private String ID;
	private String name;
	private String email;
	private String dateOfBirth;
	private String postion;
	private String departmentID;
	private String password;
	
	private Department department;
	
	public Employee(String name, String email, String dateOfBirth, String postion, String departmentID) {
		super();
		this.name = name;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.postion = postion;
		this.departmentID = departmentID;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getID() {
		return ID;
	}

	public void setID(String employeeID) {
		this.ID = employeeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPostion() {
		return postion;
	}

	public void setPostion(String postion) {
		this.postion = postion;
	}

	public String getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
