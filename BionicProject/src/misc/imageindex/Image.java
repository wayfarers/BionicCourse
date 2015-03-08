package misc.imageindex;

import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Image {
	
	public String path;
	public String name;
	public int width;
	public int height;
	public long sizeInBytes;
	public int type;
	public String md5;
	public boolean isCorrupted;
	public String format;
	public String errorMessage;
	
	public Image(File file) {
		BufferedImage  im = null;
		try {
			System.out.println("Reading image " +  file.getName());
			im = ImageIO.read(file);
			if (im == null)
				throw new IOException("Image is null");
			width = im.getWidth();
			height = im.getHeight();
			sizeInBytes = file.length();
			type = im.getType();
			
		} catch (IOException e) {
			e.printStackTrace();
			isCorrupted = true;
			errorMessage = e.getMessage();
		}
		
		name = file.getName();
		path = file.getAbsolutePath();
		format = name.substring(name.lastIndexOf('.')).toLowerCase();
		try {
			md5 = MD5Utils.getMd5(file);
		} catch (Exception e) {
			if (errorMessage != null) {
				errorMessage = e.getMessage();
			}
		}
	} 
}
