package concurrency;

public class ThreadExample implements Runnable {
	String name = "thread";
	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.println(name + " iteration" + i + "\n");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
