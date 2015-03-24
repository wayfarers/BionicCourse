package deposits;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DepoList {
	List<DepoBase> list = new ArrayList<>();
	
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
	
	public List<DepoBase> getList() {
		return list;
	}
	
	public double getPrincipal() {
		double principal = 0;
		for (DepoBase deposit : list) {
			principal += deposit.getSum();
		}
		return principal;
	}
	
	public void remove() {
		for (Iterator<DepoBase> it = list.iterator(); it.hasNext();) {
			if (it.next().getSum() < 10000) {
				it.remove();
			}
		}
	}
	
	public void printInfo() {
		for (DepoBase dep : list) {
			System.out.println("" + dep.getClass().getSimpleName().subSequence(0, 6) + "\t\t" + dep.getSum() + "\t\t" + dep.getInterest());
		}
	}
	
	public void sort() {
		Collections.sort(list);
	}
	
	public void sort2() {
		list.sort(new DepoBase.DepoComparator());
	}
	
	public void saveList() {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("depList.dat"))) {
			for (DepoBase dep : list) {
				out.writeObject(dep);
			}
			System.out.println("Saving complete");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadList() {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("depList.dat"))) {
			list.clear();
			while (true) {
				list.add((DepoBase) in.readObject());
			}
		} catch (EOFException e) {
			System.out.println("Loading complete");
			return;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	protected String[] getReportLines() {
		String [] depReport = new String[list.size() + 1];
		int count = 0;
		
		depReport[count] = String.format(
				"%1$22s %2$12s %3$12s %4$12s %5$5s\n", "Deposit Type",
				"Deposit sum", "Interest", "Start date", "Term");
		
		for (DepoBase dep : list) {
			depReport[++count] = String.format(
					"%1$22s %2$12.2f %3$12.2f %4$12s %5$5d", 
					dep.getClass().getSimpleName(), dep.getSum(), 
					dep.getInterest(), dep.getStartDate().toString(), 
					dep.getDaysLong());
		}
		
		return depReport;
	}
	
	public void generateReport(String fileName) {
		try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
			for (String string : getReportLines()) {
				out.println(string);
			}
			System.out.println("Report generated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void add100(int index) {
		DepoBase dep = list.get(index);
		double sum = dep.getSum(); 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dep.setSum(sum + 100);
		list.set(index, dep);
	}
	
}
