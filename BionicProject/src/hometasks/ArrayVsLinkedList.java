package hometasks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayVsLinkedList {
	public static void main(String[] args) {
		for (int i = 10; i <= 1000000; i *= 10) {
			calcTime(i);
		}
		System.exit(0);
	}
	
	public static void calcTime(int num) {
		List<Integer> array = new ArrayList<>();
		List<Integer> linked = new LinkedList<>();
		long time, timeArray, timeLinked;
		
		for (int i = 0; i < 50; i++) {
			array.add(i);
			linked.add(i);
		}
		
		time = System.nanoTime();
		for (int i = 0; i < num; i++) {
//			array.add(20, i);
			array.add(20 + i, i);
		}
		timeArray = System.nanoTime() - time;
		
		time = System.nanoTime();
		for (int i = 0; i < num; i++) {
//			linked.add(20, i);
			linked.add(20 + i, i);
		}
		timeLinked = System.nanoTime() - time;
		System.out.println("num: " + num + ",\ttimeArray / timeLinked: " + (1.0 * timeArray / timeLinked));
	}
}
