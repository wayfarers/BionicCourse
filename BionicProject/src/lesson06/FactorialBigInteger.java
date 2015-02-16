package lesson06;

import java.math.BigInteger;

public class FactorialBigInteger {
	public static void main(String[] args) {
		System.out.println(getFactorialLong(25));
		System.out.println(getFactorialBig(BigInteger.valueOf(25)));
		
		
		System.exit(0);
	}
	
	public static long getFactorialLong(long n) {
		long res = n;
		if (n > 1) {
			res *= getFactorialLong(n - 1);
		}
		return res;
	}
	
	public static BigInteger getFactorialBig(BigInteger n) {
		BigInteger res = n;
		if (n.compareTo(BigInteger.ONE) == 1) {
			res  = res.multiply(getFactorialBig(n.add(BigInteger.valueOf(-1))));
		}
		return res;
	}
}
