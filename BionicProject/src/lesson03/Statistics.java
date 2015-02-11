package lesson03;

import java.util.Arrays;

public class Statistics {
	public static void main(String[] args) {
		double [] data = {10.5, 10.8, 11.2, 10.9, 10.4, 10.6, 10.9, 
				   11.0, 10.3, 10.8, 10.6, 11.3, 10.5, 10.7, 
				   10.8, 10.9, 10.8, 10.7, 10.9, 11.0};
		
		double m = 0;
		double D = 0;
		double dev = 0;
		double tb = 1.96;
		double [] Ib = new double[2];
		int n = data.length;
		
		for (double e : data) {
			m += e;
		}
		
		m /= n;
		
		for (double e : data) {
			D += (e - m)*(e - m);
		}
		
		D /= (n - 1);
		dev = Math.sqrt(D / n);
		Ib[0] = Math.rint((m - tb * dev) * 100) / 100;
		Ib[1] = Math.rint((m + tb * dev) * 100) / 100;
		
		
		System.out.println("m = " + m + ", D = " + D + ", dev = " + dev);
		System.out.println("Ib = " + Arrays.toString(Ib));
	}
}
