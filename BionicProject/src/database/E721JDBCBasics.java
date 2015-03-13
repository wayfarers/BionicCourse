package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
public class E721JDBCBasics {
	public static void main(String[] args) {
		try{
			Connection con = getConnection();
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(
//					"SELECT name, charge FROM merchant");
			
			
//			while (rs.next()){
//				String nm = rs.getString("name");
//				double p = rs.getDouble(2);
//				System.out.println(nm + "   " + p);
//			}
			
//			String sql = "INSERT INTO customer (name, address, "; 
//			sql += " email, ccNo, ccType, maturity) values(";
//			sql += " 'Clar Nelis', 'Vosselaar st. 19, Trnaut, Belgium', "; 
//			sql += " 'Clar@adw.com', 	'11345694671231', ";
//			sql += " 'MasterCard', '2014-07-31') ";
//			Statement stmt = con.createStatement();
//			stmt.executeUpdate(sql);
			
//			addCustomer("Nevil Smitt", "Vosselaar st. 30, Munich, Germany", "nevil@ukr.net", "11342469701473", "MasterCard", 
//					new java.sql.Date(new GregorianCalendar(2012,03,31).getTime().getTime()));
			
			String sql = "select sum(sumpayed) from payment where merchantid = " + args[0]; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			double p = rs.getDouble(1);
			System.out.println(p);

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

	public static void addCustomer(String name, String address, String email, String ccNo, String ccType, java.sql.Date dt) throws SQLException, IOException{
		Connection con = getConnection();
		String sql = "INSERT INTO customer (name, address, "; 
		sql += " email, ccNo, ccType, maturity) values(?,?,?,?,?,?) ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setString(2, address);
		stmt.setString(3, email);
		stmt.setString(4, ccNo);
		stmt.setString(5, ccType);
		stmt.setDate(6, dt);
		stmt.executeUpdate();
		con.close();
	}

}

