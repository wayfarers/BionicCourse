package hometasks;

import deposits.DepoBase;

/**
 * Create DealAnalisys class that saves deals of any type (depo – single,
 * barrier, month capitalization, TBill. Create compareIncome method that
 * compares yield of the deal that saved in the class object and deal given as
 * method’s parameter
 * 
 * @author wayfarer
 *
 * @param <T>
 */
public class DealAnalisys<T> {
	private T deal;

	public DealAnalisys(T deal) {
		this.deal = deal;
	}

	public <K> double compareIncome(K deal2) {
		double income1 = deal instanceof DepoBase ? ((DepoBase) deal).getInterest() : ((TBill) deal).getIncome();
		double income2 = deal2 instanceof DepoBase ? ((DepoBase) deal2).getInterest() : ((TBill) deal2).getIncome();

		return Math.round((income1 - income2) * 100) / 100.0;
//		return income1 - income2;
	}
}
