package deposits;

public class ThreadTestMain {
	public static void main(String[] args) {
		DepoList list = new DepoList();
		Thread test1 = new Thread(new ThreadTest(list, 2));
		Thread test2 = new Thread(new ThreadTest(list, 2));
		System.out.println(list.getList().get(2).getSum());
		test1.start();
		test2.start();
		try {
			test1.join();
			test2.join(); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(list.getList().get(2).getSum());
		
		
	}
}
