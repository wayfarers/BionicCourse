package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MerchantApplication {
	
	
	public static void main(String[] args) {
		List<Merchant> merchants = new ArrayList<>();
		getMerchants(merchants);
		for (Merchant merchant : merchants) {
			System.out.println(merchant.toString());
		}
	}
	
	public static void getMerchants(List<Merchant> merchants) {
		try {
			Connection con = getConnection();
			String sql = "select * from merchant"; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()){
				merchants.add(new Merchant(rs.getString("name"), rs.getDouble("charge"), rs.getInt("period"), 
						rs.getDouble("minSum"), rs.getString("bankName"), rs.getString("swift"), rs.getString("account")));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
