import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		final int VERTEX_NUMBER = 200;
		List<List<Pair<Integer, Integer>>> adjacencyList = new ArrayList<>(VERTEX_NUMBER + 1);
		List<Integer> distance = new ArrayList<>(VERTEX_NUMBER + 1);
		MinHeap<Pair<Integer, Integer>> minHeap = new MinHeap<>(VERTEX_NUMBER);
		// not use index 0
		adjacencyList.add(new ArrayList<>());
		distance.add(Integer.MAX_VALUE);

		// read file into graph
		String fileName = "input.txt";
        URL resource = Main.class.getResource(fileName);
        try {
			Path filepath = Paths.get(resource.toURI());
            try {
				Files.lines(filepath)
				.map(str -> Arrays.stream(str.split("(,|\\s)+"))
				.mapToInt(Integer::parseInt).toArray())
				.forEach(
						array -> {
							int index = array[0];
							adjacencyList.add(new ArrayList<>());
							for(int i = 1; i < array.length; i += 2){
								Pair<Integer, Integer> pair = new Pair<Integer, Integer>(array[i], array[i + 1]);
								adjacencyList.get(index).add(pair);
							}
						}
				);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		// initialize
		for(int i = 1; i < VERTEX_NUMBER + 1; i ++) {
			distance.add(Integer.MAX_VALUE);
			minHeap.insert(new Pair<Integer, Integer>(i, Integer.MAX_VALUE));
		}

        // shortest path
		minHeap.decreaseVal(1, 0);
		distance.set(1, 0);

		while(!minHeap.isEmpty()) {
			int minKey = minHeap.extractMin();

			// System.out.println(Integer.toString(minKey));

			for(int i = 0; i < adjacencyList.get(minKey).size(); i ++) {
				Pair<Integer, Integer> pair = adjacencyList.get(minKey).get(i);

				int nodeName = pair.getKey();
				int nodeWeight = pair.getValue();
				int pathLength = distance.get(minKey) + nodeWeight;

				if(pathLength < distance.get(nodeName)) {
					minHeap.decreaseVal(nodeName, pathLength);
					distance.set(nodeName, pathLength);
				}
			}

		}

		// 7,37,59,82,99,115,133,165,188,197
		System.out.print(distance.get(7) + ",");
		System.out.print(distance.get(37) + ",");
		System.out.print(distance.get(59) + ",");
		System.out.print(distance.get(82) + ",");
		System.out.print(distance.get(99) + ",");
		System.out.print(distance.get(115) + ",");
		System.out.print(distance.get(133) + ",");
		System.out.print(distance.get(165) + ",");
		System.out.print(distance.get(188) + ",");
		System.out.print(distance.get(197));
	}
}
