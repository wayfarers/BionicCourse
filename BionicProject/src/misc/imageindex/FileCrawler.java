package misc.imageindex;

import java.io.File;

public abstract class FileCrawler {
	
	// TODO: Move counter and total bytes of matched files into the crawler
	
	public void crawl(String parentDir) {
		File root = new File(parentDir);
		if (root.isFile()) {
			return;
		}
		File [] files = root.listFiles();
		if (files == null) {
			return;
		}
		for (File file : files) {
			if (file.isDirectory()) {
				crawl(file.getAbsolutePath());
			} else {
				if (fileMatches(file)) {
					processFile(file);
				}
			}
		}
	}
	
	public boolean fileMatches(File f) {
		return true;
	}
	
	public abstract void processFile(File f);
}
