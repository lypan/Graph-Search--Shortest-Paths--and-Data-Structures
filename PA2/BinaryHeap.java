import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BinaryHeap<T extends Pair> {
	ArrayList<T> binaryHeap;
	ArrayList<Integer> indexMap;
	private int heapSize;
	
	public BinaryHeap(int capacity) {
		binaryHeap = new ArrayList<>(capacity + 1);
		indexMap = new ArrayList<>(capacity + 1);
		heapSize = 0;
	}
	
	public void insert(T pair) {
		heapSize ++;
		binaryHeap.ensureCapacity(heapSize + 1);
		
		binaryHeap.add(heapSize, pair);
		swim(heapSize);
	}
	
	public void swim(int k) {
		while(k > 1 && !greater(binaryHeap.get(k), binaryHeap.get(k / 2))) {
			Collections.swap(binaryHeap, k, k / 2);
			k /= 2;
		}
	}
	
	public boolean greater(T p1, T p2) {
		return p1.compareTo(p2) > 0;
	}
	
	
}
