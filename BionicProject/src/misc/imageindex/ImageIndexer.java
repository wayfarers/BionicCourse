package misc.imageindex;

import java.io.File;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

public class ImageIndexer {
	ImageDAO imageDao = new ImageDAO();
	private static final DecimalFormat DECIMAL2 = new DecimalFormat("#.##");
	private static final DecimalFormat DECIMAL3 = new DecimalFormat("#.###");
	
	enum ImageExtension {
		PNG, JPG, BMP, JPEG
	}
	
	public void indexImages(String directory) {
		
		FileCrawler crawler = new FileCrawler() {
			@Override
			public boolean fileMatches(File f) {
				return isImage(f.getName());
			}
		};
		System.out.println("Counting the images...");
		crawler.crawl(directory);
		System.out.println("Total " + crawler.count + " images found");
		
		processFiles(crawler, System.nanoTime());
		
	}
	
	public void processFiles(FileCrawler crawler, long startTime) {
		double percentComplete;
		long proceededSize = 0;
		double timeLeft = 0;
		for (File f : crawler.machedFiles) {
			try {
				// TODO: Add progress bar by file sizes (also add ETA) (Done)
				imageDao.saveIfNotExists(new Image(f));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			proceededSize += f.length();
			percentComplete = proceededSize * 100.0 / crawler.totalSize;
			timeLeft = (System.nanoTime() - startTime) / percentComplete * (100 - percentComplete) / 1e9 / 60;
			
			System.out.println(DECIMAL2.format(percentComplete) + "% complete, " + DECIMAL3.format(timeLeft) + " min left");
		}
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
