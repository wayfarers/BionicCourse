package lesson04;

public class Palindrome {
	public static void main(String[] args) {
		StringBuilder s = new StringBuilder("aredivider");
		if (s.toString().equals(s.reverse().toString())) {
			System.out.println("The word is a palindrome");
		} else {
			System.out.println("The word isn't a palindrome");
		}

	}
}
