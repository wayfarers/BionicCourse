package misc.imageindex;

import java.io.File;
import java.sql.SQLException;

public class ImageIndexer {
	ImageDAO imageDao = new ImageDAO();
	
	enum ImageExtension {
		PNG, JPG, BMP, JPEG
	}
	
	public void indexImages(String directory) {
		final long [] startTime = {0};
		
		FileCrawler crawler = new FileCrawler() {
			@Override
			public void processFiles() {
				double perComplete;
				int proceededSize = 0;
				double timeLeft = 0;
				for (File f : machedFiles) {
					try {
						// TODO: Add progress bar by file sizes (also add ETA) (Done)
						imageDao.saveIfNotExists(new Image(f));
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					proceededSize += f.length();
					perComplete = proceededSize * 100.0 / totalSize;
					timeLeft = (System.nanoTime() - startTime[0]) / perComplete * (100 - perComplete) / 1e9 / 60;
					System.out.println(Math.round(perComplete * 100) / 100.0 + "% complete, " + 
							Math.round(timeLeft * 1000) / 1000.0 + " min left");
				}
			}
			
			@Override
			public boolean fileMatches(File f) {
				return isImage(f.getName());
			}
		};
		System.out.println("Counting the images...");
		crawler.crawl(directory);
		System.out.println("Total " + crawler.count + " images found");
		
		startTime[0] = System.nanoTime();
		crawler.processFiles();
		
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
