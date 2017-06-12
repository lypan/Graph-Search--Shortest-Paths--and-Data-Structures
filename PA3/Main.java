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
import java.util.PriorityQueue;

public class Main {

	private static int median = 0;
	public static void main(String[] args) {
		final int NUM_LEN = 10000;
		PriorityQueue<Integer> maxPQ = new PriorityQueue<>(NUM_LEN, Collections.reverseOrder());
		PriorityQueue<Integer> minPQ = new PriorityQueue<>(NUM_LEN);
		List<Integer> medianArray = new ArrayList<>(NUM_LEN);
		
		// read file into graph
		String fileName = "input.txt";
        URL resource = Main.class.getResource(fileName);
        try {
			Path filepath = Paths.get(resource.toURI());
            try {
				Files.lines(filepath)
				.mapToInt(Integer::parseInt)
				.forEach(
						value -> {
							int lowSize = maxPQ.size();
							int highSize = minPQ.size();
							
							// case 1
							if(lowSize == highSize){
								if(value < median) {
									maxPQ.add(value);
									median = maxPQ.peek();
								}
								else {
									minPQ.add(value);
									median = minPQ.peek();
								}
							}
							// case 2
							else if(lowSize > highSize) {
								if(value < median) {
									maxPQ.add(value);
									minPQ.add(maxPQ.poll());
									median = maxPQ.peek();
								}
								else {
									minPQ.add(value);
									median = maxPQ.peek();
								}								
							}
							// case 3
							else if(lowSize < highSize) {
								if(value < median) {
									maxPQ.add(value);
									median = maxPQ.peek();									
								}
								else {
									minPQ.add(value);
									maxPQ.add(minPQ.poll());
									median = maxPQ.peek();
								}								
							}
							
							medianArray.add(median);
						}
						);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
        
        int modResult = 0;
        for(Integer val : medianArray) {
        	modResult += (val % NUM_LEN);
        	modResult %= NUM_LEN;
        }
        
        System.out.println(modResult);

	}
}
