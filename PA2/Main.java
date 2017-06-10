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
		final int VERTEX_NUMBER = 8;
		List<List<Pair<Integer, Integer>>> adjacencyList = new ArrayList<>(VERTEX_NUMBER + 1);
		List<Integer> distance = new ArrayList<>(VERTEX_NUMBER + 1);
		Heap<Pair<Integer, Integer>> minHeap = new Heap<>(VERTEX_NUMBER);
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
			minHeap.insert(new Pair<>(i, i));
		}

        // shortest path
        System.out.println("test");

	}
}
