
public class Main {

	public static void main(String[] args) {
      Graph graph = new Graph();
      graph.initFromFile("scc.txt");
      graph.reverseGraph();
      graph.pass1();
      graph.pass2();
      graph.topFive();
	}

}
