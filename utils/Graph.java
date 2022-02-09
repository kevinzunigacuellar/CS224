package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/* Graph class represents a undirected graph using adjacency list representation.
 * params:
 *  N: number of nodes in the graph
 * adj: adjacency list
 * 
 * methods:
 * addEdge(int u, int v): add an edge to the graph
 * BFS(int s): perform BFS on the graph starting from node s
 * 
 * @author Kevin Zuniga Cuellar
 * @version 1.0
 * 
 */

public class Graph {

  private int N;
  private LinkedList<Integer> adj[];

  public Graph(int n) {
    this.N = n;
    this.adj = new LinkedList[N];
    for (int i = 0; i < N; i++) {
      adj[i] = new LinkedList<>();
    }
  }

  public void addEdge(int v, int u) {
    adj[v].add(u);
    adj[u].add(v);
  }

  public void BFS(int s) {

    // initialize variables: discovered, layer, edges and layerCount
    boolean discovered[] = new boolean[N];
    ArrayList<ArrayList<Integer>> layer = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
    int layerCount = 0;
    // set s node as discovered
    discovered[s] = true;
    System.out.println("Discovered node: " + s);
    // add the first layer to the list
    layer.add(new ArrayList<Integer>(Arrays.asList(s)));

    while (layer.get(layerCount).size() != 0) {
      // initialize the next layer to the list
      layer.add(new ArrayList<Integer>());
      // iterate through the nodes in the current layer
      for (int i = 0; i < layer.get(layerCount).size(); i++) {
        // get node from the current layer
        int u = layer.get(layerCount).get(i);
        // iterate through the neighbors of the current node
        for (int j = 0; j < adj[u].size(); j++) {
          // get the neighbor node of the current node
          int v = adj[u].get(j);
          // if the neighbor node is not discovered
          if (!discovered[v]) {
            System.out.println("Discovered node: " + v);
            // add node to the discovered list
            discovered[v] = true;
            // add the neighbor node to the next layer
            layer.get(layerCount).add(v);
            // add the edge to the list of edges
            edges.add(new ArrayList<Integer>(Arrays.asList(u, v)));
          }
        }
      }
      // increment the layer count
      layerCount++;
    }
    System.out.println(edges);

  }
}
