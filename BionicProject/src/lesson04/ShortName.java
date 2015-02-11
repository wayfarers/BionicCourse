package lesson04;

public class ShortName {
	public static void main(String[] args) {
		String fullName = "Sushko Evgeniy Alexandrovich";
		String initials;
		
		String [] splitName = fullName.split(" ");
		initials = splitName[1].charAt(0) + "." + splitName[2].charAt(0) + ".";
		System.out.println(splitName[0] + " " + initials);
	}
}
