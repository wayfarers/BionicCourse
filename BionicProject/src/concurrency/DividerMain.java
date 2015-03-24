package concurrency;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DividerMain {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();
		long [] data = {100L,14325L, 43218899L, 789L, 71268L};
		
		for (long value : data) {
			results.add(exec.submit(new DividerCount(value)));
		}
		
		for (Future<String> fs : results) {
			try {
				if (fs.isDone()){
					System.out.println(fs.get());
				}
			} catch (InterruptedException e) {
				System.out.println(e);
				return;
			} catch (ExecutionException e) {
				System.out.println(e);
			} finally {
				exec.shutdown();
			}
		}
	}
}

