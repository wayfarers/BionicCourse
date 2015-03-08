package misc.imageindex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ImageDAO {
	private static Connection con = null;
	private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String url = "jdbc:derby:";
	private static String dbName = "../ImageDB";
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ImageDAO() {
	}
	
	public void clearData() throws SQLException {
		executeUpdate("delete from Image");
	}

	private void executeUpdate(String sql) throws SQLException {
		Connection con = DriverManager.getConnection(url + dbName + ";");
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
		con.close();
	}

	public void save(Image image) throws SQLException {
		// TODO: Size in bytes
		String sql = String.format("insert into Image(path, name, format, width, height, imageType, md5, isCorrupted) values('%s', '%s', '%s', %d, %d, %d, '%s', %b)",
				image.path, image.name, image.format, image.width, image.height, image.type, image.md5, image.isCorrupted
				);
		executeUpdate(sql);
	}

	public void saveIfNotExists(Image image) throws SQLException {
		// TODO (check..)
		save(image);
	}
}
