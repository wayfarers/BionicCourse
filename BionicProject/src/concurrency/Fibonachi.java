package concurrency;

import java.util.ArrayList;

public class Fibonachi {
	static class CalcFibonachi extends Thread {
		ArrayList<Integer> list;
		int n;
		
		
		public CalcFibonachi(ArrayList<Integer> list, int n) {
			this.list = list;
			this.n = n;
		}


		@Override
		public void run() {
			if(n == 0) {
				list.add(0);
				return;
			} else if (n == 1) {
				list.add(0);
				list.add(1);
				return;
			}
			list.add(0);
			list.add(1);
			for (int i = 2; i <= n; i++) {
				list.add(list.get(i - 1) + list.get(i - 2));
			}
		}
	}
	
	static class PrintFibonachi extends Thread{
		Thread thread;
		ArrayList<Integer> list;

		public PrintFibonachi(ArrayList<Integer> list, Thread thread) {
			this.list = list;
			this.thread = thread;
		}
			
		@Override
		public void run() {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (Integer integer : list) {
				System.out.print(integer + " ");
			}
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		Thread fib = new CalcFibonachi(list, 20);
		fib.start();
		new PrintFibonachi(list, fib).start();
	}
}
