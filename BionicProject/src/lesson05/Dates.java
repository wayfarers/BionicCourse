package lesson05;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Dates {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		//		Date currentDate = new Date();
		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
		//		System.out.println("Current date (using Date class): " + f.format(currentDate));
		//		
		Calendar calendar = new GregorianCalendar();
		//		calendar.getInstance();
		//		System.out.println("Current date (using Calendar class): " + f.format(calendar.getTime()));
		//		
		//		calendar.getInstance();
		//		calendar.add(Calendar.WEEK_OF_YEAR, 6);
		//		System.out.println("+ 6 weeks: " + f.format(calendar.getTime()));
		//		
		//		calendar.getInstance();
		//		calendar.add(Calendar.MONTH, -4);
		//		System.out.println("- 6 month: " + f.format(calendar.getTime()));
		//		
		//		calendar.getInstance();
		//		calendar.add(Calendar.DAY_OF_YEAR, 45);

		calendar.getInstance();
		System.out.println("Current date: " + f.format(calendar.getTime()));

		System.out.println(f.format(getNextBankDay(calendar.getTime())));

	}

	public static Date getNextBankDay(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);

		if (day == Calendar.FRIDAY) {
			calendar.add(Calendar.DAY_OF_YEAR, 3);
		} else if (day == Calendar.SATURDAY) {
			calendar.add(Calendar.DAY_OF_YEAR, 2);
		} else {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		return calendar.getTime();
	}
}
