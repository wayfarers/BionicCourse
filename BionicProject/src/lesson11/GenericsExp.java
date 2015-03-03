package lesson11;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a class with list of objects of an arbitrary given class with two methods:
 * - add for accumulation data in the list
 * - printList with a boolean parameter to print odd or even elements of the list accordingly to parameter’s value

 * @author wayfarer
 */
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
