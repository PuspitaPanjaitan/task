package crud_sys;

import java.sql.*;

public class ConnectDB {
	
	
	public static Connection getConnection() {
		Connection connection =null;
		
		
		try {
			
			
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javatest", "root", "" );
			
		
		}catch (Exception e) {
					connection = null;

			 }
		
		return connection;
	}
		
		
}
	




