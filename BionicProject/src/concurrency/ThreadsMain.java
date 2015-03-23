package concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadsMain {
	public static void main(String[] args) {
		Thread t1 = new Thread(new ThreadExample());
		Thread t2 = new Thread(new RunnableExample(t1));
		
		Executor e = Executors.newCachedThreadPool();
		e.execute(t1);
		e.execute(t2);
		
		
	}
}
