// package Week8;

public class MyStackThreadSafeComplete {
	private final int maxSize;
	private long[] stackArray; // guarded by "this"
	private int top; //invariant: top < stackArray.length && top >= -1; guarded by "this"

	// pre-condition: s > 0
	// post condition: maxSize == s && top == -1 && stackArray != null
	public MyStackThreadSafeComplete(int s) {
		maxSize = s;
		stackArray = new long[maxSize];
		top = -1;
	}

	// pre-condition: top < stackArray.length
	// post-condition: new element is inserted
	public synchronized void push(long j) {
		while (isFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		stackArray[++top] = j;
		notifyAll();
	}

	// pre-condition: top >= 0
	// post-condition: the top element is removed
	public synchronized long pop() {
		long toReturn;

		while (isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		toReturn = stackArray[top--];
		notifyAll(); // notify all other threads that state has changed
		return toReturn;
	}

	// pre-condition: top >= 0
	// post-condition: return the current top element
	public synchronized long peek() {
		long toReturn;

		while (isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		toReturn = stackArray[top];
		notifyAll(); // notify all other threads that state has changed
		return toReturn;
	}

	// pre-condition: true
	// post-condition: the elements are un-changed. the return value is true iff the stack is empty.
	public synchronized boolean isEmpty() {
		return (top == -1);
	}

	// pre-condition: true
	// post-condition: the elements are un-changed. the return value is true iff the stack is empty.
	public synchronized boolean isFull() {
		return (top == maxSize - 1);
	}
}