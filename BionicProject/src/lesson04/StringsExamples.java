package lesson04;

public class StringsExamples {
	public static void main(String[] args) {
		String s1 = "123";
		String s2 = new String("123");
		if(s1 == s2)
			System.out.println("y");
		else
			System.out.println("n");
	}
}
