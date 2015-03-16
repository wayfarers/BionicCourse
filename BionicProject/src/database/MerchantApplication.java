package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Properties;

public class MerchantApplication {
	
	
	public static void main(String[] args) {
		Connection con = getConnection();
		printReport(con);
//		List<Merchant> merchants = new ArrayList<>();
//		getMerchants(merchants);
//		for (Merchant merchant : merchants) {
//			System.out.println(merchant.toString());
//		}
		
		
		
//		addPayment(2, 3, "CD disks", 150.5, LocalDate.now());
		
		System.exit(0);
	}
	
	public static void getMerchants(List<Merchant> merchants) {
		try (Connection con = getConnection()) {
			String sql = "select * from merchant"; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()){
				merchants.add(new Merchant(rs.getInt("id"), rs.getString("name"), rs.getDouble("charge"), rs.getInt("period"), 
						rs.getDouble("minSum"), rs.getString("bankName"), rs.getString("swift"), rs.getString("account")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static Connection getConnection() {
		try {
			Connection conn = null;
			Properties props = new Properties();
			InputStreamReader in = new InputStreamReader(new FileInputStream(
					"appProperties.txt"), "UTF-8");
			props.load(in);
			in.close();

			String connString = props.getProperty("DBConnectionString");
			conn = DriverManager.getConnection(connString);
			return conn;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void addPayment(Connection con, int mer_id, int cost_id, String goods, double sumPayed, LocalDate date) {
		double chargePayed = sumPayed * getCharge(con, mer_id) / 100;
		java.sql.Timestamp dt = new java.sql.Timestamp(java.util.Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
		
		try {
			String sql = "insert into payment (dt, merchantId, customerId, goods, sumPayed, chargePayed) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setTimestamp(1, dt);
			stmt.setInt(2, mer_id); 
			stmt.setInt(3, cost_id);
			stmt.setString(4, goods);
			stmt.setDouble(5, sumPayed);
			stmt.setDouble(6, chargePayed);
			stmt.executeUpdate();
			stmt.close();
			
			sql = "update merchant set needtosend = needtosend + ? where id = ?";
			PreparedStatement stmt2 = con.prepareStatement(sql);
			stmt2.setDouble(1, sumPayed - chargePayed);
			stmt2.setInt(2, mer_id);
			stmt2.executeUpdate();
			stmt2.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static double getCharge(Connection con, int mer_id) {
		double charge = 0;
		try {
			String sql = "select charge from merchant where id =" + mer_id; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			charge = rs.getDouble("charge");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return charge;
	}
	
	public static void printReport(Connection con) {
		try {
			String sql = "select merchantid, sum(chargepayed) from PAYMENT group by MERCHANTID";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			System.out.println("Mer_id\tincome");
			while (rs.next()) {
				System.out.println("" + rs.getInt(1) + "\t" + rs.getDouble(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void generatePayList(Connection con) {
		try {
			String sql = "select id, needtosend, minsum, lastsent from merchant";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int id;
			double needToSend;
			double minsum;
			Date dt;
			while (rs.next()) {
				id = rs.getInt(1);
				needToSend = rs.getDouble(2);
				minsum = rs.getDouble(3);
				dt = rs.getDate(4);
				if (needToSend > minsum || dt.after(Date.valueOf(LocalDate.now()))) {
					String insertSql = "insert into paylist(merchantid, sumSent, snetDate, status) values()";
					PreparedStatement stmt2 = con.prepareStatement(sql);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * table: sumsent, sentdate timestamp, status char
	 * create table paylist 
(
	id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
	merchantId INT CONSTRAINT mer_fk references merchant,
	sumSent DECIMAL(15,2),
	sentDate timestamp,
	status char(1) not null,
	primary key (id)
)
	 */
	
	
}

/**
 * Presentation layer: Web site, Standalone app, ...
 * Service layer: PaymentService, ShopService
 * Entities: Merchant, Customer
 * DAO layer: MerchantDAO, CustomerDAO, ... - CRUD operations
 * DataSource, ConnectionPool..
 */