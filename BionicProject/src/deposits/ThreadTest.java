package deposits;

public class ThreadTest implements Runnable {
	DepoList list;
	int index;
	
	public ThreadTest(DepoList list, int index) {
		this.list = list;
		this.index = index;
	}
	
	@Override
	public void run() {
		list.add100(index);
	}
	
}
