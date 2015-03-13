package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class E721JDBCBasics {
	public static void main(String[] args) {
		try{
//			Connection con = DriverManager.getConnection 	("jdbc:derby:..\\CM");
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT name, charge FROM merchant");
			while (rs.next()){
				String nm = rs.getString("name");
				double p = rs.getDouble(2);
				System.out.println(nm + "   " + p);
			}
			con.close();
		}
		catch(SQLException ex){
			System.out.println("Error " + ex.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws IOException, SQLException {
		Connection conn = null;
		Properties props = new Properties();
		InputStreamReader in = new InputStreamReader(new FileInputStream(
				"appProperties.txt"), "UTF-8");
		props.load(in);
		in.close();

		String connString = props.getProperty("DBConnectionString");
		conn = DriverManager.getConnection(connString);
		return conn;
	}

}

