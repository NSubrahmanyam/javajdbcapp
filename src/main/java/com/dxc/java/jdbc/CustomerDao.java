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

	/**
	 * captilizeCustomerName
	 * @param connection
	 * @param customerName
	 * @return
	 * @throws SQLException
	 */
	// PostgreSQL : SELECT UPPER('hello')
	public String captilizeCustomerName(Connection connection, String customerName) throws SQLException {
		String upperCustomerName = "";
		String sql1 = "SELECT UPPER('hello') FROM dual"; 

		Statement statement = connection.createStatement();
		statement.execute(sql1);

		return upperCustomerName;

	}
	
	/**
	 * getSubString
	 * @param connection
	 * @throws SQLException
	 */
	// PostgreSQL : SELECT SUBSTRING('DATABASE', 1, 4) 
	public void getSubString(Connection connection) throws SQLException {
		String sql1 = "SELECT SUBSTR('DATABASE', 1, 4) FROM dual"; 
		Statement statement = connection.createStatement();
		statement.execute(sql1);
	}
	
	/**
	 * getCurrentDate
	 * @param connection
	 * @throws SQLException
	 */
	// SELECT CURRENT_TIMESTAMP -- PostgreSQL
	public void getCurrentDate(Connection connection) throws SQLException {
		String sql1 = "SELECT SYSDATE FROM dual"; 
		Statement statement = connection.createStatement();
		statement.execute(sql1);
	}
	
	/**
	 * addMonthsToCurrentDate
	 * @param connection
	 * @throws SQLException
	 */
	// Oracle:  SELECT ADD_MONTHS(SYSDATE, 2) FROM dual;
	//  PostgreSQL:  SELECT CURRENT_DATE + INTERVAL '2 months';
	public void addMonthsToCurrentDate(Connection connection,Integer noOfMonths) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ADD_MONTHS(SYSDATE,");
		query.append(noOfMonths);
		query.append(" )");
		query.append("FROM dual"); 
		Statement statement = connection.createStatement();
		statement.execute(query.toString());
	}
	
	/**
	 * 
	 * @param connection
	 * @throws SQLException
	 */
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
	
	//Type conversion functions
	//String to number
	//PostgreSQL: SELECT CAST('5000' AS INTEGER);  
	public void convertToNumber(Connection connection) throws SQLException {
		String sql1 = "SELECT TO_NUMBER('5000') FROM dual"; 
		Statement statement = connection.createStatement();
		statement.execute(sql1);
	}
	
	//Convert date into string
	//PostgreSQL: SELECT TO_CHAR(CURRENT_DATE, 'DD-MM-YYYY'); 
	public void convertDateToString(Connection connection) throws SQLException {
		String sql1 = "SELECT TO_CHAR(SYSDATE, 'DD-MM-YYYY') FROM dual"; 
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