package misc.imageindex;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileCrawler {
	
	// TODO: Move counter and total bytes of matched files into the crawler (Done)
	int count = 0;
	long totalSize = 0;
	public List<File> machedFiles = new ArrayList<>();
	
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
					totalSize += file.length();
					machedFiles.add(file);
					if (count++ % 100 == 0)
						System.out.println("Found " + count + " files, total of " + totalSize / (1024 * 1024) + "Mb");
					processFile(file);
				}
			}
		}
	}
	
	public boolean fileMatches(File f) {
		return true;
	}
	
	public void processFile(File file) {
		
	}
}
