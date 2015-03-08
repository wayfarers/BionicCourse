package misc.imageindex.tests;

import java.io.File;

import misc.imageindex.FileCrawler;

public class CrawlerTest {
	public static void main(String[] args) {
		new FileCrawler() {
			@Override
			public void processFile(File f) {
				System.out.println(f.getName());
			}
		}.crawl("C:/");
	}
}
