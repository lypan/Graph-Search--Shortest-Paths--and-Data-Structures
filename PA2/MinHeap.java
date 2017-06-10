import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MinHeap<T extends Pair> {
	ArrayList<T> minHeap;
	int[] indexMap;
	private int heapSize;
	private int heapCapacity;

	public MinHeap(int capacity) {
		minHeap = new ArrayList<>(capacity + 1);
		indexMap = new int[capacity + 1];
		heapSize = 0;
		heapCapacity = capacity + 1;
		minHeap.add(null);
	}

	public void insert(T pair) {
		heapSize ++;

		// add element
		minHeap.add(heapSize, pair);

		// add index
		indexMap[(int)pair.getKey()] = heapSize;

		// heapify
		swim(heapSize);
	}

	public void swim(int k) {
		while(k > 1 && !greater(minHeap.get(k), minHeap.get(k / 2))) {

			// swap index
			exchIdx(k, k / 2);

			// swap element
			Collections.swap(minHeap, k, k / 2);

			k /= 2;
		}
	}

	public void sink(int k) {
		while(2 * k <= heapSize) {
			int j = 2 * k;
			// if it has right child, compare and choose the smaller child
			if(j < heapSize && greater(minHeap.get(j), minHeap.get(j + 1)))j ++;
			// compare parent and child
			if(!greater(minHeap.get(k), minHeap.get(j)))break;

			// swap index
			exchIdx(k, j);

			// swap element
			Collections.swap(minHeap, k, j);

			k = j;
		}
	}

	public int extractMin() {
		int minKey = (int)minHeap.get(1).getKey();

		// swap index
		exchIdx(1, heapSize);

		// swap element
		Collections.swap(minHeap, 1, heapSize);

		heapSize --;

		// heapify
		sink(1);

		return minKey;
	}

	public void decreaseVal( int k, int v) {
		int kId = indexMap[k];
		minHeap.get(kId).setValue(v);

		swim(kId);
	}

	public boolean isEmpty() {
		return heapSize == 0;
	}

	public boolean greater(T p1, T p2) {
		return p1.compareTo(p2) >= 0;
	}

	public void exch(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public void exchIdx(int i, int j) {
		int iKey = (int)minHeap.get(i).getKey();
		int jKey = (int)minHeap.get(j).getKey();

		exch(indexMap, iKey, jKey);
	}

}
