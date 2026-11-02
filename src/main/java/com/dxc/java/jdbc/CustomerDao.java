package com.dxc.java.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDao {

	// String functions
	public PreparedStatement findCustomer(Connection connection, long id) throws SQLException {

		String sql = "SELECT customer_id, NVL(customer_desc, 'UNKNOWN') FROM customer WHERE customer_id = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, id);
		return ps;
	}

	//PostgreSQL:
	/**
	 * -- PostgreSQL
		SELECT
	    CASE
	        WHEN email IS NOT NULL THEN 'Available'
	        ELSE 'Not Available'
	    END
		FROM employees;
	 */
	
	public void isEmailAvailable(Connection connection) throws SQLException {
		String sql1 = "SELECT NVL2(email, 'Available', 'Not Available')  FROM employees";
		
		Statement statement = connection.createStatement();
		statement.execute(sql1);
	}
	
		
	
	public PreparedStatement findCustomers(Connection connection,int maxRows) throws SQLException {

        String sql = "SELECT customer_id,customer_name,created_date FROM customer WHERE ROWNUM <= ? ORDER BY created_date DESC";

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, maxRows);

        return ps;
    }
	
	

	
	
	
}