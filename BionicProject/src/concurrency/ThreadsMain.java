package concurrency;

public class ThreadsMain {
	public static void main(String[] args) {
		Thread t1 = new ThreadExample();
		Thread t2 = new Thread(new RunnableExample(t1));
		
		t1.start();
		t2.start();
		
		
	}
}
