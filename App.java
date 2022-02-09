import utils.Graph;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Graph g = new Graph(9);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 4);
    g.addEdge(2, 5);
    g.addEdge(2, 3);
    g.addEdge(3, 5);
    g.addEdge(3, 7);
    g.addEdge(3, 8);
    g.addEdge(4, 5);
    g.addEdge(5, 6);
    g.addEdge(7, 8);

    Scanner userInput = new Scanner(System.in);
    System.out.println("Enter a node to search (1 - 8): ");
    int node = userInput.nextInt();
    g.BFS(node);
    userInput.close();
  }

}
