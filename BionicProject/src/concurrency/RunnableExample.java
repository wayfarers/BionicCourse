package concurrency;

public class RunnableExample implements Runnable{
	Thread thread;
	
	public RunnableExample() {
	}
	public RunnableExample(Thread thread) {
		this.thread = thread;
	}
	String name = "runnable";
	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.println("\t" + name + " iteration" + i + "\n");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (i == 30) {
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
