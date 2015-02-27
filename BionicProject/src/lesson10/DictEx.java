package lesson10;

import java.util.TreeMap;

public class DictEx {
	TreeMap<String, String> map = new TreeMap<>();
	
	public DictEx() {
		init();
	}
	
	public void init() {
		map.put("Save", "Запомнить");
		map.put("Remove", "Удалить");
		map.put("Cancel", "Прервать");
		map.put("Create", "Создать");
		map.put("Ok", null);
	}
	
	public String getTranslate(String word) {
		if (map.get(word) != null) {
			return map.get(word);
		}
		return word;
	}
}
