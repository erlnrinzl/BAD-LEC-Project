package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDAO extends DatabaseConfig {
	private DepartmentDAO departmentDAO = new DepartmentDAO();
	
	public void create(Employee employee) {
		String sql = "INSERT INTO msemployee (employeeID, employeeName, employeeEmail, employeeDOB, employeePosition, employeeDepartmentID, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, employee.getID());
			stmt.setString(2, employee.getName());
			stmt.setString(3, employee.getEmail());
			stmt.setString(4, employee.getDateOfBirth());
			stmt.setString(5, employee.getPostion());
			stmt.setString(6, employee.getDepartmentID());
			stmt.setString(7, employee.getPassword());
			
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Employee> read() {
		List<Employee> employees = new ArrayList<>();
		String sql = "SELECT * FROM msemployee";
		
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Employee employee = new Employee(
					rs.getString("employeeName"), 
					rs.getString("employeeEmail"),
					rs.getString("employeeDOB"),
					rs.getString("employeePosition"),
					rs.getString("employeeDepartmentID")
				);
				employee.setID(rs.getString("employeeID"));
				employee.setDepartment(departmentDAO.getDepartmentByID(employee.getDepartmentID()));				
				
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	      return employees;
	}
	
	public void update(Employee employee) {
		// only update employee data outside id, email and password
		String sql = "UPDATE msemployee SET employeeName = ?, employeeDOB = ?, employeePosition = ?, employeeDepartmentID = ? WHERE employeeID = ?";
		
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getDateOfBirth());
			stmt.setString(3, employee.getPostion());
			stmt.setString(4, employee.getDepartmentID());
			stmt.setString(5, employee.getID());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Employee employee) {
		String sql = "DELETE FROM msemployee WHERE employeeID = ?";
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, employee.getID());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    public String generateEmployeeID() {
        String sql = "SELECT * FROM msemployee ORDER BY employeeID DESC LIMIT 1";
        try {
        	Connection conn = getConnection();
        	PreparedStatement stmt = conn.prepareStatement(sql);
        	ResultSet rs = stmt.executeQuery();
        	
            if (rs.next()) {
            	String lastEmployeeID = rs.getString("employeeID");
                Integer nextEmployeeID = Integer.parseInt(lastEmployeeID.split("E")[1]) + 1;
                return String.format("E%03d", nextEmployeeID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "E001";
    }
	
	public Employee getEmployeeByID(String employeeID) {
		String sql = "SELECT * FROM msemployee WHERE employeeID = ?";
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, employeeID);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				Employee employee =  new Employee(
						rs.getString("employeeName"), 
						rs.getString("employeeEmail"), 
						rs.getString("employeeDOB"), 
						rs.getString("employeePosition"), 
						rs.getString("employeeDepartmentID")
						);
				employee.setID(rs.getString("employeeID"));
				
				return employee;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public Employee getEmployeeByEmail(String email) {
		String sql = "SELECT * FROM msemployee WHERE employeeEmail = ?";
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				Employee employee =  new Employee(
						rs.getString("employeeName"), 
						rs.getString("employeeEmail"), 
						rs.getString("employeeDOB"), 
						rs.getString("employeePosition"), 
						rs.getString("employeeDepartmentID")
						);
				employee.setID(rs.getString("employeeID"));
				employee.setDepartment(departmentDAO.getDepartmentByID(employee.getDepartmentID()));
				return employee;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean isValidPassword(String email, String password) {
		  String sql = "SELECT EXISTS (SELECT 1 FROM msemployee WHERE employeeEmail = ? AND password = ?)";
		  try {
		    Connection conn = getConnection();
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setString(1, email);
		    stmt.setString(2, password);
		    ResultSet rs = stmt.executeQuery();

		    if (rs.next()) {
		      return true;
		    } else {
		      return false;
		    }
		  } catch (SQLException e) {
		    e.printStackTrace();
		    return false;
		  }
		}
}
