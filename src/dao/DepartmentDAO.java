package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Department;
import model.Employee;

public class DepartmentDAO extends DatabaseConfig {
	public List<Department> read() {
		List<Department> departments = new ArrayList<>();
		String sql = "SELECT * FROM msdepartment";
		
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				departments.add(new Department(
						rs.getString("DepartmentID"), 
						rs.getString("departmentName")
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	      return departments;
	}
	
	public Department getDepartmentByID(String departmentID) {
		String sql = "SELECT * FROM msdepartment WHERE departmentID = ?";
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, departmentID);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				Department department =  new Department(
						rs.getString("DepartmentID"), 
						rs.getString("departmentName")
						);
				return department;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
