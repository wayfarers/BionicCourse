package lesson06;

public class ExTaskSum {
	public static void main(String[] args) {
		int sum = 0;

		for (String str : args) {
			try {
				sum += Integer.valueOf(str);
			} catch (Exception e) {
				System.out.println("***Wrong input: only integers are accepted***\n\t-wrong argument will be ignored");
			}
		}
		System.out.println(sum);
		
		System.exit(0);
	}
}
