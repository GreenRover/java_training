package ch.mtrail.concurrency;

/**
 * Puts boolean values in an array, increases the index with every write until
 * the initial capacity is reached.
 *
 */
public class ArrayStore {

	private int currentIndex = -1;
	private final boolean[] data;

	public ArrayStore(int capacity) {
		this.data = new boolean[capacity];
	}

	public void setBoolean(boolean valueToSet) {
		// int indexToUse = currentIndex;
		// indexToUse++;
		// data[indexToUse] = valueToSet;
		// currentIndex = indexToUse;
		data[++currentIndex] = valueToSet;
	}

	public boolean[] getArray() {
		return data;
	}

}
