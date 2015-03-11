package misc.imageindex.tests;

import java.io.File;

import misc.imageindex.FileCrawler;

public class CrawlerTest {
	public static void main(String[] args) {
		new FileCrawler() {
			@Override
			public void processFiles() {
				for (File f : machedFiles) {
					System.out.println(f.getName());
				}
			}
		}.crawl("C:/");
	}
}
