package hometasks;

import java.util.ArrayList;
import java.util.List;

public class PrimeDividers {
	public static void main(String[] args) {
		int n = 40;
		System.out.println(findPrimeDividers(n).toString());
	}
	
	public static List<Integer> findPrimeDividers(int n) {
		List<Integer> prime = new ArrayList<>();
		List<Integer> div = findDividers(n);
		
		for (Integer num : div) {
			if (isPrime(num)) {
				prime.add(num);
			}
		}
		
		return prime;
	}
	
	public static boolean isPrime(Integer num) {
		if (num == 2) {
			return true;
		} else {
			for (int j = 3; j <= Math.ceil(Math.sqrt(num)); j += 2) {
				if (num % j == 0) {
					return false;
				}
			}
			return true;
		}
	}
	
	public static List<Integer> findDividers(int n) {
		List<Integer> list = new ArrayList<>();
		if (n % 2 == 0) {
			list.add(new Integer(2));
		}
		
		for (int i = 3; i <= n; i += 2) {
			if (n % i == 0) {
				list.add(new Integer(i));
			}
		}
		
		return list;
	}
}
