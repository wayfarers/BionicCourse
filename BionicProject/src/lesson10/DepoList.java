package lesson10;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import deposits.*;

public class DepoList {
	ArrayList<Deposit> list = new ArrayList<>();
	
	public DepoList() {
		init();
	}
	
	public void init() {
		list.add(new SimpleDeposit(18, 2500, LocalDate.of(2013, 9, 8), 61));
		list.add(new MonthCapitalizeDeposit(21, 10000, LocalDate.of(2012, 2, 1), 181));
		list.add(new SimpleDeposit(15.3, 5500, LocalDate.of(2013, 11, 12), 30));
		list.add(new BarrierDeposit(19.56, 43000, LocalDate.of(2011, 12, 18), 370));
		list.add(new MonthCapitalizeDeposit(16, 12000, LocalDate.of(2013, 7, 12), 91));
	}
	
	public double getPrincipal() {
		double principal = 0;
		for (Deposit deposit : list) {
			principal += deposit.getSum();
		}
		return principal;
	}
	
	public void remove() {
		for (Iterator<Deposit> it = list.iterator();it.hasNext();) {
			if (it.next().getSum() < 10000) {
				it.remove();
			}
		}
	}
}
