package misc.imageindex;

import java.io.File;
import java.sql.SQLException;

public class ImageIndexer {
	
	ImageDAO imageDao = new ImageDAO();
	
	enum ImageExtension {
		PNG, JPG, BMP, JPEG
	}
	
	public void indexImages(String directory) {
		
		System.out.println("Counting the images...");
		System.out.println("Total " + countImages(directory) + " images found");
		new FileCrawler() {
			@Override
			public void processFile(File f) {
				if (isImage(f.getName())) {
					try {
						// TODO: Add progress bar by file sizes (also add ETA)
						imageDao.saveIfNotExists(new Image(f));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			}
		}.crawl(directory);
	}
	
	public int countImages(String directory) {
		final int[] count = new int[1];
		final long[] totalSize = new long[1];
		new FileCrawler() {
			@Override
			public void processFile(File f) {
				if (isImage(f.getName())) {
					totalSize[0] += f.length();
					if (count[0]++ % 100 == 0)
						System.out.println("Found " + count[0] + " images, total of " + totalSize[0] / (1024 * 1024) + "Mb");
				}
				
			}
		}.crawl(directory);
		
		return count[0];
	}
	
	private boolean isImage(String fileName) {
		fileName = fileName.toLowerCase();
		for (ImageExtension extension : ImageExtension.values()) {
			if (fileName.endsWith(extension.toString().toLowerCase())) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws SQLException {
		// TODO: Scan the whole drive
		// TODO: Do interesting quieries (dublicates, largest images, and so on). Probably optimise the DB (create indices)
		new ImageDAO().clearData();
		new ImageIndexer().indexImages("C:/");
	}
}
