package com.ibm.fsd.my.stock.bkg.schedule;

public class TimerThread implements Runnable {

	public void run() {
		int i = 0;
		boolean b = true;
		while (b) {

			if (i == 10) {
				b = false;
			}
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName().toString() + i);
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
