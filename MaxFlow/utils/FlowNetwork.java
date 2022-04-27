package utils;

import java.util.LinkedList;

public class FlowNetwork {

  private int V;
  private LinkedList<FlowEdge>[] adj;

  // constructor
  public FlowNetwork(int V) {
    this.V = V;
    adj = (LinkedList<FlowEdge>[]) new LinkedList[V];
    for (int v = 0; v < V; v++) {
      adj[v] = new LinkedList<FlowEdge>();
    }
  }

  // getters
  public int V() {
    return V;
  }

  public void addEdge(FlowEdge e) {
    int v = e.from();
    int w = e.to();
    adj[v].add(e); // forward edge
    adj[w].add(e); // backward edge
  }

  public Iterable<FlowEdge> adj(int v) {
    return adj[v];
  }

}
