package lesson12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class DictEx2 {
	TreeMap<String, String> map = new TreeMap<>();
	
	public DictEx2() {
	}
	
	public void init() {
		map.put("Save", "Запомнить");
		map.put("Remove", "Удалить");
		map.put("Cancel", "Прервать");
		map.put("Create", "Создать");
		map.put("Ok", null);
	}
	
	public void init2() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("dict.txt"));
		String line;
		while((line = reader.readLine()) != null) {
			String [] s = line.split("-");
			map.put(s[0], s[1]);
		}
		reader.close();
	}
	
	public String getTranslate(String word) {
		if (map.get(word) != null) {
			return map.get(word);
		}
		return word;
	}
}
