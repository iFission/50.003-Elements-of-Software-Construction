package Week5;

import java.math.BigInteger;

public class FactorPrimeMultiThreadNoInterrupt {
	public static void main(final String[] args) throws InterruptedException {
		final int threadCount = 4;
		BigInteger[] result = new BigInteger[threadCount];

		// final BigInteger n = new BigInteger("144");
		final BigInteger n = new BigInteger("412163");
		// final BigInteger n = new BigInteger("4294967297");

		// create array of threads
		final FactorNoInterrupt[] factorThreads = new FactorNoInterrupt[threadCount];

		// assign values to threads, start thread
		for (int i = 0; i < threadCount; i++) {
			factorThreads[i] = new FactorNoInterrupt(n, i + 2, threadCount);
			factorThreads[i].start();
		}

		// wait for thread to finish, print result
		for (int i = 0; i < threadCount; i++) {
			factorThreads[i].join();
			result[i] = factorThreads[i].getResult();
			System.out.println(result[i]);
		}

	}

}

class FactorNoInterrupt extends Thread {
	private BigInteger n, start, step;
	private BigInteger result;

	public FactorNoInterrupt(final BigInteger n, final int start, final int step) {
		this.n = n;
		this.start = BigInteger.valueOf(start);
		this.step = BigInteger.valueOf(step);
		// System.out.println(n);
		System.out.println("start is :" + start);
		System.out.println("step is :" + step);
	}

	public void run() {
		System.out.println("running thread");
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
				return start;
			}

			start = start.add(step);
		}

		assert (false); //if this is reached, an error occurs.
		return null;
	}
}