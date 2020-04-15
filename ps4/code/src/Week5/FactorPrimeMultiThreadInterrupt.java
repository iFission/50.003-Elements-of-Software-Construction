package Week5;

import java.math.BigInteger;

public class FactorPrimeMultiThreadInterrupt {
	public static boolean found;

	public static void main(final String[] args) throws InterruptedException {
		final int threadCount = 5;
		final BigInteger[] result = new BigInteger[threadCount];

		// final BigInteger n = new BigInteger("144");
		// final BigInteger n = new BigInteger("412163");
		final BigInteger n = new BigInteger("4294967297");

		// create array of threads
		final FactorInterrupt[] factorThreads = new FactorInterrupt[threadCount];

		// assign values to threads, start thread
		for (int i = 0; i < threadCount; i++) {
			factorThreads[i] = new FactorInterrupt(n, i + 2, threadCount);
			factorThreads[i].start();
		}

		// check if a thread has finished, busy wait
		while (!found) {
		}

		// kill other threads
		for (int i = 0; i < threadCount; i++) {
			if (factorThreads[i].getResult() == null) {
				factorThreads[i].interrupt();
			} else {
				System.out.println(result[i]);
			}
			System.out.println(result[i]);
		}

	}

}

class FactorInterrupt extends Thread {
	private final BigInteger n;
	private BigInteger start;
	private final BigInteger step;
	private BigInteger result;
	Boolean found;

	public FactorInterrupt(final BigInteger n, final int start, final int step) {
		this.n = n;
		this.start = BigInteger.valueOf(start);
		this.step = BigInteger.valueOf(step);
		// System.out.println(n);
		System.out.println("start is :" + start);
		System.out.println("step is :" + step);
	}

	public void run() {
		result = factor(n);
	}

	/**
	 * @return BigInteger return the n
	 */
	public BigInteger getResult() {
		return result;
	}

	//Precondition: n is a semi-prime number.
	//Postcondition: the returned value is a prime factor of n;
	public BigInteger factor(final BigInteger n) {
		final BigInteger zero = new BigInteger("0");

		while (start.compareTo(n) < 0) {
			if (n.remainder(start).compareTo(zero) == 0) {
				System.out.println("factor is " + start);
				result = start;
				FactorPrimeMultiThreadInterrupt.found = true;
			}
			if (this.isInterrupted()) {
				System.out.println("interrupted");
				break;
			}

			start = start.add(step);
		}

		assert (false); //if this is reached, an error occurs.
		return null;
	}
}