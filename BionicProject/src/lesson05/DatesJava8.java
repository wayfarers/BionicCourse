package lesson05;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatesJava8 {
	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter form = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		System.out.println("Current date: " + date.format(form));
		
		date = date.plusWeeks(6);
		System.out.println("Date in 6 weeks: " + date.format(form));
		
		date = LocalDate.now();
		date = date.plusMonths(-4);
		System.out.println("Date before 4 month: " + date.format(form));
		
		date = LocalDate.now();
		date = date.plusDays(45);
		System.out.println("Date in 45 days: " + date.format(form));
		
		date = LocalDate.now();
		date = getNextBankDay(date);
		System.out.println("NextBankDay: " + date.format(form));
	}
	
	public static LocalDate getNextBankDay(LocalDate date) {
		switch (date.getDayOfWeek()) {
		case FRIDAY:
			date = date.plusDays(3);
			break;
		case SATURDAY:
			date = date.plusDays(2);
			break;
		default:
			date = date.plusDays(1);
		}
		return date;
	}
}
