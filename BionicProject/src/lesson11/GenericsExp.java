package lesson11;

import java.util.ArrayList;
import java.util.List;

public class GenericsExp<T> {
	List<T> list = new ArrayList<>();
	
	public void add(T obj) {
		list.add(obj);
	}

	public void printList(boolean odd) {
		int i = odd ? 0 : 1;
		for (; i < list.size(); i += 2) {
			System.out.println(list.get(i).toString());
		}
	}
}
