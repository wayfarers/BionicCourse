package misc;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class DaysLived {
	public static void main(String[] args) {
		String date = "1990-05-10";
		LocalDate birthdayDate = getDate(date);
		getBirthdayData(birthdayDate);
	}
	
	public static LocalDate getDate(String s) {
		LocalDate birthdayDate = null;
		try {
			birthdayDate = LocalDate.parse(s);
		} catch (DateTimeException e){
			System.out.println("Wrong date format. Try again.");
			
		}
		return birthdayDate;
	}
	
	public static void getBirthdayData(LocalDate date) {
		int daysLived = 0;
		LocalDate nowDate = LocalDate.now();
		String dayOfWeek = date.getDayOfWeek().toString();
		Period period = Period.between(date, nowDate);
		LocalDate leap = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
		
		if (leap.isLeapYear() && leap.getMonthValue() > 2) {
			leap = leap.plusYears(1);
		}
		
		while (leap.isBefore(nowDate)) {
			if (leap.isLeapYear()) {
				daysLived++;
			}
			leap = leap.plusYears(1);
		}
		
		daysLived += period.getYears() * 365;
		
		int curMonth = date.getMonthValue();	
		for (int i = 1; i <= period.getMonths(); i++) {
			if (curMonth > 12) {
				curMonth = 1;
			}
			daysLived += Month.of(curMonth).length(false);
			curMonth++;
		}
		
		daysLived += period.getDays();
		
		System.out.println("You was born at " + dayOfWeek);
		System.out.println("You are " + daysLived + " days old.");
		
	}
	
}
