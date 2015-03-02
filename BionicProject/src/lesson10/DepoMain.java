package lesson10;

import java.util.ArrayList;

import deposits.DepoBase;

public class DepoMain {
	public static void main(String[] args) {
		DepoList deps = new DepoList();
//		deps.sort();
//		deps.printInfo();
		ArrayList<DepoBase> list = deps.getList();
//		list
//		.stream()
//		.filter(d -> d.getInterestRate() > 16.5)
//		.forEach(d -> System.out.format("sum = %1$8.2f   interestRate = %2$7.2f\n", d.getSum(), d.getInterestRate()));

//		double avg = list
//				.stream()
//				.filter(d -> d.getInterestRate() > 16.5)
//				.mapToDouble(DepoBase::getSum)
//				.average()
//				.getAsDouble();
//			System.out.println("Average sum = " + avg);
		double sum = list
				.stream()
				.mapToDouble(DepoBase::getSum)
				.reduce(0, (a, b) -> a + b);
			System.out.println("General sum = " + sum);

		
		System.exit(0);
	}
}
