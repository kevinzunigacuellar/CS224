
import java.util.Queue;
import java.util.LinkedList;
import utils.FlowNetwork;
import utils.FlowEdge;

/* 
  * References: Coursera Algorithms Part II, Princeton University
*/

class MaxFlow {
  private boolean[] marked;
  private FlowEdge[] edgeTo;
  private double value;

  // getters
  public double value() {
    return value;
  }

  // methods
  public MaxFlow(FlowNetwork G, int s, int t) {
    value = 0.0;
    while (hasAugmentingPath(G, s, t)) {

      // Augment and calculate bottle
      double bottle = augment(s, t);
      System.out.println("Augmenting flow amount added " + bottle);

      // update flow in Gf and print augmenting path
      System.out.print("t <- ");
      for (int v = t; v != s; v = edgeTo[v].other(v)) {
        System.out.print(edgeTo[v].to() + " <- ");
        edgeTo[v].addResidualFlowTo(v, bottle);
      }
      System.out.print("s");
      System.out.println();

      // update max value
      value += bottle;
    }
  }

  public double augment(int s, int t) {
    double bottle = Double.POSITIVE_INFINITY;
    for (int v = t; v != s; v = edgeTo[v].other(v)) {
      bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
    }
    return bottle;
  }

  private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
    edgeTo = new FlowEdge[G.V()];
    marked = new boolean[G.V()];

    Queue<Integer> q = new LinkedList<Integer>();
    q.add(s);
    marked[s] = true;
    while (!q.isEmpty()) {
      int v = q.remove();
      for (FlowEdge e : G.adj(v)) {
        int w = e.other(v);
        if (e.residualCapacityTo(w) > 0 && !marked[w]) {
          edgeTo[w] = e;
          marked[w] = true;
          q.add(w);
        }
      }
    }
    return marked[t];
  }

  public static void main(String[] args) {
    System.out.println("Ford Fulkerson Max Flow (Adjecency List) \n");
    // create empty flow network
    FlowNetwork G = new FlowNetwork(9);

    // add edges to flow network (s=1, t=8)
    G.addEdge(new FlowEdge(1, 2, 10.0));
    G.addEdge(new FlowEdge(1, 3, 5.0));
    G.addEdge(new FlowEdge(1, 4, 15.0));
    G.addEdge(new FlowEdge(2, 3, 4.0));
    G.addEdge(new FlowEdge(2, 5, 9.0));
    G.addEdge(new FlowEdge(2, 6, 15.0));
    G.addEdge(new FlowEdge(3, 4, 4.0));
    G.addEdge(new FlowEdge(3, 6, 8.0));
    G.addEdge(new FlowEdge(4, 7, 30.0));
    G.addEdge(new FlowEdge(5, 6, 15.0));
    G.addEdge(new FlowEdge(5, 8, 10.0));
    G.addEdge(new FlowEdge(6, 7, 15.0));
    G.addEdge(new FlowEdge(6, 8, 10.0));
    G.addEdge(new FlowEdge(7, 3, 6.0));
    G.addEdge(new FlowEdge(7, 8, 10.0));

    // compute maximum flow
    MaxFlow maxFlow = new MaxFlow(G, 1, 8);
    System.out.println();
    System.out.println("The max flow is: " + maxFlow.value());

  }
}