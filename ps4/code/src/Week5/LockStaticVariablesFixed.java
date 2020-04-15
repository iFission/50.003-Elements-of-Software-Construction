package Week5;

import java.util.HashMap;
import java.util.Map;

public class LockStaticVariablesFixed {
	public static Map<String, Integer> asset = new HashMap<String, Integer>();

	public static void main(String args[]) {
		asset.put("saving", 5000);
		asset.put("cash", 0);
		int numberofThreads = 10000;
		WD[] threads = new WD[numberofThreads];

		for (int i = 0; i < numberofThreads; i++) {
			threads[i] = new WD();
			threads[i].start();
		}

		try {
			for (int i = 0; i < numberofThreads; i++) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			System.out.println("some thread is not finished");
		}

		System.out.print("The result is: " + LockStaticVariablesFixed.asset.get("cash"));
	}
}

class WD extends Thread {
	public void run() {
		synchronized (LockStaticVariablesFixed.asset) {
			if (LockStaticVariablesFixed.asset.get("saving") >= 1000) {
				System.out.println("I am doing something.");
				LockStaticVariablesFixed.asset.put("saving", LockStaticVariablesFixed.asset.get("saving") - 1000);
				LockStaticVariablesFixed.asset.put("cash", LockStaticVariablesFixed.asset.get("cash") + 1000);
			}
		}
	}
}
