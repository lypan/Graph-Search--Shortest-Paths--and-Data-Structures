import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Graph {
    private Map<Integer,List<Integer>> originalGraph;
    private Map<Integer,Boolean> originalVisited;
    private Stack<Integer> orderStack;
    private Map<Integer,List<Integer>> reversedGraph;
    private Map<Integer,Boolean> reverseVisited;
    private PriorityQueue<Integer> countPQ;
    private int MAX_NO = 875714;
    private int count;

    public Graph() {
        originalGraph = new HashMap<>();
        reversedGraph = new HashMap<>();

        originalVisited = new HashMap<>();
        reverseVisited = new HashMap<>();

        orderStack = new Stack<>();
        countPQ = new PriorityQueue<>(1024, Collections.reverseOrder());
    }

    public void initFromFile(String fileName) {
        URL resource = Graph.class.getResource(fileName);
        try {
            Path filepath = Paths.get(resource.toURI());
            try {
                Files.lines(filepath)
                        .map(str -> Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray())
                        .forEach(
                                array -> {
                                    int key = array[0];
                                    int value = array[1];
                                    if(originalGraph.get(key) == null)originalGraph.put(key, new ArrayList<Integer>());
                                    originalGraph.get(key).add(value);
                                }
                        );
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void printGraph(Map<Integer,List<Integer>> map) {
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int key = entry.getKey();
            List<Integer> value = entry.getValue();
            System.out.print("Key: " + key);
            System.out.println("");


            System.out.print("Value: ");
            for(Integer val : value) System.out.print(val + " ");
            System.out.println("");

        }
    }

    public void reverseGraph() {

        for(Map.Entry<Integer, List<Integer>> entry : originalGraph.entrySet()) {
            int key = entry.getKey();
            List<Integer> value = entry.getValue();

            for(Integer val : value) {
                if(reversedGraph.get(val) == null)reversedGraph.put(val, new ArrayList<Integer>());
                reversedGraph.get(val).add(key);
            }
        }
    }

    public void pass1() {

        for(int i = 1; i <= MAX_NO ; i ++) {
            if(reverseVisited.getOrDefault(i, false))continue;
            dfs1(i);
        }
    }

    public void pass2() {

        while(!orderStack.empty()) {
            int node = orderStack.pop();
            count = 0;
            if(originalVisited.getOrDefault(node, false)){
                countPQ.add(0);
                continue;
            }

            originalVisited.put(node, true);
            count ++;
            List<Integer> nodeList = originalGraph.get(node);

            if(nodeList != null) {
                for(Integer val : nodeList) {
                    if(!originalVisited.getOrDefault(val, false)) {
                        dfs2(val);
                    }
                }
            }

            countPQ.add(count);
        }
    }

    private void dfs1(int node) {
        reverseVisited.put(node, true);

        List<Integer> nodeList = reversedGraph.get(node);
        if(nodeList != null) {
            for(Integer val : nodeList) {
                if(!reverseVisited.getOrDefault(val, false)) {
                    dfs1(val);
                }
            }
        }

        orderStack.push(node);

    }
    private void dfs2(int node) {
        originalVisited.put(node, true);
        count ++;

        List<Integer> nodeList = originalGraph.get(node);
        for(Integer val : nodeList) {
            if(!originalVisited.getOrDefault(val, false)) {
                dfs2(val);
            }
        }
    }

    public void topFive() {
        for(int i = 0; i < 5; i ++) {
            System.out.println(countPQ.poll());
        }
    }

}
