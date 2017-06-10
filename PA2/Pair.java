import java.util.AbstractMap;
import java.util.Map.Entry;

public class Pair<K,V> extends AbstractMap.SimpleEntry<K,V> implements Comparable<Pair<K,V>>{

	public Pair(K k, V v) {
		super(k, v);
	}

	@Override
	public int compareTo(Pair<K, V> o) {

		return (int) getValue() - (int) o.getValue();
	}

}
