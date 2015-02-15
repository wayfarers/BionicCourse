package hometasks;

public class Strings {

	public static void main(String[] args) {
		long time1, time2, timeS, timeSB;
		System.out.println("gnerateString:\n");
		for (int i = 10; i <= 100000; i *= 10) {
			time1 = System.nanoTime();
			generateString(i);
			time2 = System.nanoTime();
			timeS = time2 - time1;

			time1 = System.nanoTime();
			generateStringBuiler(i);
			time2 = System.nanoTime();
			timeSB = time2 - time1;
			System.out.println("n = " + i + ": StringBuilder is x" + 1.0
					* timeS / timeSB + " times faster.");
		}

	}

	public static String generateString(int n) {
		String res = "";
		for (int i = 0; i < n; i++) {
			res += i + " ";
		}
		return res;
	}

	public static StringBuilder generateStringBuiler(int n) {
		StringBuilder res = new StringBuilder("");
		for (int i = 0; i < n; i++) {
			res.append(i + " ");
		}
		return res;
	}
}