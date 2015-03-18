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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MerchantApplication {
	
	
	public static void main(String[] args) {
		Connection con = getConnection();
		printReport(con);
		generatePayList(con);
//		List<Merchant> merchants = getMerchants();
//		for (Merchant merchant : merchants) {
//			System.out.println(merchant.toString());
//		}
		
		
		
//		addPayment(2, 3, "CD disks", 150.5, LocalDate.now());
		
		System.exit(0);
	}
	
	public static List<Merchant> getMerchants() {
		List<Merchant> merchants = new ArrayList<>();
		try (Connection con = getConnection()) {
			String sql = "select * from merchant"; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()){
				merchants.add(new Merchant(rs.getInt("id"), rs.getString("name"), rs.getDouble("charge"), rs.getInt("period"), 
						rs.getDouble("minSum"), rs.getString("bankName"), rs.getString("swift"), rs.getString("account"), 
						rs.getDouble("needToSend"), rs.getDouble("sent"), rs.getDate("lastSent")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return merchants;
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
		Timestamp dt = new Timestamp(java.util.Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
		
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
		List<Merchant> merchants = getMerchants();
		for (Merchant merchant : merchants) {
			double needToSend = merchant.getNeedToSend();
			double minsum = merchant.getMinSum();
			Date dt = merchant.getLastSent();
			LocalDate compareDate = LocalDate.now();
			switch (merchant.getPeriod()) {
			case WEEKLY:
				compareDate = compareDate.minusWeeks(1);
				break;
			case BEWEEKLY:
				compareDate = compareDate.minusWeeks(2);
				break;
			case MONTHLY:
				compareDate = compareDate.minusMonths(1);
				break;
			}
			try {
				if (needToSend > minsum || dt.after(Date.valueOf(compareDate))) {
					String sql = "insert into paylist(merchantid, sumSent, sentDate, status) values(?,?,?,?)";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setInt(1, merchant.getId());
					stmt.setDouble(2, needToSend);
					stmt.setDate(3, Date.valueOf(LocalDate.now()));
					stmt.setInt(4, 0);
					stmt.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
 * 
 * 
 * 
 * ClientApplication
 *   ClientDAO, MerchantDAO
 *     RepositoryCOnfig (dbName, password, username, driveName)..
 */