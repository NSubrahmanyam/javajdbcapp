package com.dxc.java.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDao {

	// String functions
	public PreparedStatement findCustomer(Connection connection, long id) throws SQLException {

		String sql = "SELECT customer_id, COALESCE(customer_desc, 'UNKNOWN') FROM customer WHERE customer_id = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, id);
		return ps;
	}

	/**
	 * captilizeCustomerName
	 * @param connection
	 * @param customerName
	 * @return
	 * @throws SQLException
	 */
	public String captilizeCustomerName(Connection connection, String customerName) throws SQLException {
		String upperCustomerName = "";
		String sql1 = "SELECT UPPER('hello') FROM dual"; // PostgreSQL : SELECT UPPER('hello')

		Statement statement = connection.createStatement();
		statement.execute(sql1);

		return upperCustomerName;

	}
	
	/**
	 * getSubString
	 * @param connection
	 * @throws SQLException
	 */
	public void getSubString(Connection connection) throws SQLException {
		String sql1 = "SELECT SUBSTR('DATABASE', 1, 4) FROM dual"; // PostgreSQL : SELECT SUBSTRING('DATABASE', 1, 4) 
		Statement statement = connection.createStatement();
		statement.execute(sql1);
	}
	
	/**
	 * getCurrentDate
	 * @param connection
	 * @throws SQLException
	 */
	public void getCurrentDate(Connection connection) throws SQLException {
		String sql1 = "SELECT SYSDATE FROM dual"; // SELECT CURRENT_TIMESTAMP -- PostgreSQL
		Statement statement = connection.createStatement();
		statement.execute(sql1);
	}
	
	/**
	 * addMonthsToCurrentDate
	 * @param connection
	 * @throws SQLException
	 */
	public void addMonthsToCurrentDate(Connection connection,Integer noOfMonths) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ADD_MONTHS(SYSDATE,");
		query.append(noOfMonths);
		query.append(" )");
		query.append("FROM dual"); // Oracle:  SELECT ADD_MONTHS(SYSDATE, 2) FROM dual;
								  //  PostgreSQL:  SELECT CURRENT_DATE + INTERVAL '2 months';
		Statement statement = connection.createStatement();
		statement.execute(query.toString());
	}
	
	public void isEmailAvailable(Connection connection) throws SQLException {
		String sql1 = "SELECT NVL2(email, 'Available', 'Not Available')  FROM employees";
		/**
		 * -- PostgreSQL
		SELECT
	    CASE
	        WHEN email IS NOT NULL THEN 'Available'
	        ELSE 'Not Available'
	    END
		FROM employees;
		 */
		Statement statement = connection.createStatement();
		statement.execute(sql1);
	}
	
	//Type conversion functions
	//String to number
	public void convertToNumber(Connection connection) throws SQLException {
		String sql1 = "SELECT TO_NUMBER('5000') FROM dual"; //PostgreSQL: SELECT CAST('5000' AS INTEGER);  
		Statement statement = connection.createStatement();
		statement.execute(sql1);
	}
	
	//Convert date into string
	public void convertDateToString(Connection connection) throws SQLException {
		String sql1 = "SELECT TO_CHAR(SYSDATE, 'DD-MM-YYYY') FROM dual"; //PostgreSQL: SELECT TO_CHAR(CURRENT_DATE, 'DD-MM-YYYY'); 
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