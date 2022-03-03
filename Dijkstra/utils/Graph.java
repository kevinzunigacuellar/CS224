package Dijkstra.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graph {
  int vertices;
  LinkedList<Edge>[] adjacencylist;

  public Graph(int vertices) {
    this.vertices = vertices;
    adjacencylist = new LinkedList[vertices];
    // initialize adjacency lists for all the vertices
    for (int i = 0; i < vertices; i++) {
      adjacencylist[i] = new LinkedList<>();
    }
  }

  public void addEgde(int source, int destination, int weight) {
    Edge edge = new Edge(source, destination, weight);
    // one way for directed graph
    adjacencylist[source].add(edge);
  }

  public void printGraph() { // print the graph
    for (int i = 0; i < vertices; i++) {
      LinkedList<Edge> list = adjacencylist[i];
      for (int j = 0; j < list.size(); j++) {
        System.out.println("vertex-" + i + " is connected to " +
            list.get(j).destination + " with weight " + list.get(j).weight);
      }
    }
  }

  public void addNodetoPath(int node, ArrayList<ArrayList<Integer>> parent) {
    for (int i = 0; i < parent.size(); i++) {
      parent.get(i).add(node);
    }
  }

  public Edge extractMin(PriorityQueue<Edge> pq) {
    Edge min = pq.poll();
    return min;
  }

  public void changeKey(PriorityQueue<Edge> pq, int o, int w, int weight) {
    pq.add(new Edge(w, w, weight));
  }

  public int[] dijkstra(Graph G, int s) {

    System.out.println("Graph is represented using an adjacency list.");
    // create a priority queue. Comparing the weights of the edges
    PriorityQueue<Edge> pq = new PriorityQueue<Edge>(vertices, (a, b) -> a.weight - b.weight);

    // initialize discovered array
    boolean[] discovered = new boolean[vertices];

    // initialize distance array
    int[] distance = new int[vertices];
    for (int i = 1; i < vertices; i++) {
      distance[i] = Integer.MAX_VALUE;
    }

    // initialize parent array
    ArrayList<ArrayList<Integer>> parent = new ArrayList<ArrayList<Integer>>();
    for (int i = 0; i < vertices; i++) {
      parent.add(new ArrayList<Integer>());
    }

    // initialize distance and pq with source
    distance[s] = 0;
    pq.add(new Edge(s, s, 0));

    while (!pq.isEmpty()) {

      // extract min value
      Edge edge = extractMin(pq);
      int v = edge.destination;
      int o = edge.source;

      // set node to discovered and print path
      if (!discovered[v]) {
        discovered[v] = true;
        System.out.println("Node " + v + " included in S with the shortest path length " + distance[v]);
      }

      // get its adjacency list and iterate over it
      LinkedList<Edge> v_adj = G.adjacencylist[v];
      for (int i = 0; i < v_adj.size(); i++) {
        int w = v_adj.get(i).destination;

        // if not discovered
        if (!discovered[w]) {
          int weight = v_adj.get(i).weight;

          // if distance is greater than the weight of the edge + distance of the source
          if (distance[v] + weight < distance[w]) {
            // add to parent
            parent.get(w).add(v_adj.get(i).source);

            // update distance
            distance[w] = distance[v] + weight;

            // add to pq
            changeKey(pq, o, w, distance[w]);
          }
        }
      }
    }

    for (int a = 0; a < parent.size(); a++) {
      System.out.print("Path: " + a + ": ");
      for (int b = 0; b < parent.get(a).size(); b++) {
        System.out.print(parent.get(a).get(b));
        if (b != parent.get(a).size() - 1) {
          System.out.print(" -> ");
        }
      }
      System.out.println();
    }
    return distance;
  }
}
