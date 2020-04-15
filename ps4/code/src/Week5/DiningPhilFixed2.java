// package Week10;

import java.util.Random;

public class DiningPhilFixed2 {
	/*
	first N-1 phil grabs left fork first
	last phil grabs right fork first
	*/

	private static int N = 5;

	public static void main(String[] args) throws Exception {
		Philosopher[] phils = new Philosopher[N - 1];
		PhilosopherRight philsRight;
		Fork[] forks = new Fork[N];

		for (int i = 0; i < N; i++) {
			forks[i] = new Fork(i);
		}

		// start first N-1 phil
		for (int i = 0; i < N - 1; i++) {
			phils[i] = new Philosopher(i, forks[i], forks[(i + N - 1) % N]);
			phils[i].start();
		}

		// start last phil
		// philsRight = new PhilosopherRight(N - 1, forks[(N + N - 2) % N], forks[N - 1]);

		philsRight = new PhilosopherRight(N - 1, forks[N - 1], forks[(N + N - 2) % N]);
		philsRight.start();

	}
}

class PhilosopherRight extends Thread {
	private final int index;
	final Fork left;
	final Fork right;

	public PhilosopherRight(int index, Fork left, Fork right) {
		this.index = index;
		this.left = left;
		this.right = right;
	}

	public void run() {
		Random randomGenerator = new Random();
		try {
			while (true) {
				Thread.sleep(randomGenerator.nextInt(100)); //not sleeping but thinking
				System.out.println("Phil " + index + " finishes thinking.");
				right.pickup();
				System.out.println("Phil " + index + " picks up right fork.");
				left.pickup();
				System.out.println("Phil " + index + " picks up left fork.");
				Thread.sleep(randomGenerator.nextInt(100)); //eating
				System.out.println("Phil " + index + " finishes eating.");
				left.putdown();
				System.out.println("Phil " + index + " puts down left fork.");
				right.putdown();
				System.out.println("Phil " + index + " puts down right fork.");
			}
		} catch (InterruptedException e) {
			System.out.println("Don't disturb me while I am sleeping, well, thinking.");
		}
	}
}

class Philosopher extends Thread {
	private final int index;
	final Fork left;
	final Fork right;

	public Philosopher(int index, Fork left, Fork right) {
		this.index = index;
		this.left = left;
		this.right = right;
	}

	public void run() {
		Random randomGenerator = new Random();
		try {
			while (true) {
				Thread.sleep(randomGenerator.nextInt(100)); //not sleeping but thinking
				System.out.println("Phil " + index + " finishes thinking.");
				left.pickup();
				System.out.println("Phil " + index + " picks up left fork.");
				right.pickup();
				System.out.println("Phil " + index + " picks up right fork.");
				Thread.sleep(randomGenerator.nextInt(100)); //eating
				System.out.println("Phil " + index + " finishes eating.");
				left.putdown();
				System.out.println("Phil " + index + " puts down left fork.");
				right.putdown();
				System.out.println("Phil " + index + " puts down right fork.");
			}
		} catch (InterruptedException e) {
			System.out.println("Don't disturb me while I am sleeping, well, thinking.");
		}
	}
}

class Fork {
	private final int index;
	private boolean isAvailable = true;

	public Fork(int index) {
		this.index = index;
	}

	public synchronized void pickup() throws InterruptedException {
		while (!isAvailable) {
			wait();
		}

		isAvailable = false;
		notifyAll();
	}

	public synchronized void putdown() throws InterruptedException {
		while (isAvailable) {
			wait();
		}

		isAvailable = true;
		notifyAll();
	}

	public String toString() {
		if (isAvailable) {
			return "Fork " + index + " is available.";
		} else {
			return "Fork " + index + " is NOT available.";
		}
	}
}