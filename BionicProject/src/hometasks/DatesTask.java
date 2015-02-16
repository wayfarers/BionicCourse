package hometasks;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class DatesTask {
	public static void main(String[] args) {
		LocalDate birthdayDate = LocalDate.of(1990, 5, 10);
		getBirthdayData(birthdayDate);
		System.out.println();
		calculateEasterDate();
	}
	
	public static void getBirthdayData(LocalDate date) {
		String birthday = "You are ";
		String dayOfWeek = date.getDayOfWeek().toString();
		Period period = Period.between(date, LocalDate.now());
		birthday += (period.getMonths() + period.getYears() * 12) + " months and " + period.getDays() + " days old.";
		System.out.println("You was born at " + dayOfWeek);
		System.out.println(birthday);
		
//		return new String[] {birthday, dayOfWeek};
	}
	
	public static void calculateEasterDate() {
		/*
		 * Для определения даты Православной пасхи по старому стилю необходимо:
    	Разделить номер года на 19 и определить остаток от деления a.
    	Разделить номер года на 4 и определить остаток от деления b.
    	Разделить номер года на 7 и определить остаток от деления c.
    	Разделить сумму 19a + 15 на 30 и определить остаток d.
    	Разделить сумму 2b + 4c + 6d + 6 на 7 и определить остаток e.
    	Определить сумму f = d + e.
    	Если f ≤ 9, то Пасха будет праздноваться 22 + f марта; если f > 9, то Пасха будет праздноваться f — 9 апреля.
		Для перевода на новый стиль дату, как известно, нужно сдвинуть вперёд на 13 дней в 20-м и 21-м веках
		 */
		LocalDate date = LocalDate.now();
		int a = date.getYear() % 19;
		int b = date.getYear() % 4;
		int c = date.getYear() % 7;
		int d = (19 * a + 15) % 30;
		int e = (2 * b + 4 * c + 6 * d + 6) % 7;
		int f = d + e;
		LocalDate easterDate;
		LocalDate trinityDate;
		
		if (f <= 9) {
			easterDate = LocalDate.of(date.getYear(), Month.MARCH, 22 + f);
		} else {
			easterDate = LocalDate.of(date.getYear(), Month.APRIL, f - 9);
		}
		if (date.getYear() >= 1901 && date.getYear() <= 2101) {
			easterDate = easterDate.plusDays(13);
		}
		
		trinityDate = easterDate.plusDays(49);
		System.out.println("Easter date is " + easterDate.toString());
		System.out.println("Trinity date is " + trinityDate.toString());
		
	}
}
