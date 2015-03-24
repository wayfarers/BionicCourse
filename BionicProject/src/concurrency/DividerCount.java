package concurrency;

import java.util.concurrent.Callable;

public class DividerCount implements Callable<String> {
	long value;
	int dividers;
	
	public DividerCount(long val) {
		this.value = val;
	}
	@Override
	public String call() throws Exception {
		for (long i = 1; i <= value; i++) {
			if (value % i == 0) {
				dividers++;
			}
		}
		return "Value: " + value + ", " + dividers + " dividers";
	}
		
}
