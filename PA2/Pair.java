import java.util.AbstractMap;
import java.util.Map.Entry;

public class Pair<K,V> extends AbstractMap.SimpleEntry<K,V> implements Comparable<Pair<K,V>>{

	public Pair(Entry<? extends K, ? extends V> entry) {
		super(entry);
	}

	@Override
	public int compareTo(Pair<K, V> o) {
		
		return (int) getValue() - (int) o.getValue();
	}

}
