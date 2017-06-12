import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

	public static int count = 0;
	public static void main(String[] args) {
		Map<Long, Long> map = new HashMap<>();

		String fileName = "input.txt";
        URL resource = Main.class.getResource(fileName);
        try {
			Path filepath = Paths.get(resource.toURI());
            try {
				Files.lines(filepath)
				.mapToLong(Long::parseLong)
				.forEach(
						value -> {
							map.put(value, 1L);
						}
				);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

        for(Long target = -10000L; target <= 10000L; target ++) {
        	System.out.println(target);
        	for(Map.Entry<Long, Long> entry : map.entrySet()) {
        		Long current = entry.getKey();
        		Long other = target - current;

        		if(!current.equals(other) && map.containsKey(other)) {
        			count ++;
        			break;
        		}
        	}

        }

        System.out.println(count);

	}
}
