package concurrency;

public class RunnableExample implements Runnable{
	Thread t;
	
	public RunnableExample() {
	}
	public RunnableExample(Thread t) {
		this.t = t;
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
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
