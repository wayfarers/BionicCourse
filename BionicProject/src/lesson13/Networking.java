package lesson13;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Read file from 
 * http://www.ukrstat.gov.ua/express/expr2012/09_12/234.zip
 * and save it in test.zip file
 * @author oper4
 *
 */
public class Networking {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.ukrstat.gov.ua/express/expr2012/09_12/234.zip"); 
		InputStream in = url.openStream();
		FileOutputStream out = new FileOutputStream("test.zip");
		int inputInt;
		while ((inputInt = in.read()) != -1) 
			out.write(inputInt);
		in.close();
		out.close();

	}
}
