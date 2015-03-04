package lesson10;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import deposits.BarrierDeposit;
import deposits.DepoBase;
import deposits.MonthCapitalizeDeposit;
import deposits.SimpleDeposit;

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
}
