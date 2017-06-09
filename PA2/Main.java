import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
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
							Pair<Integer, Integer> pair = new Pair<>();
						}
				);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
