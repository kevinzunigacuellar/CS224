package utils;

public class FlowEdge {

  // variables
  private int v; // from
  private int w; // to
  private double capacity;
  private double flow;

  // constructor
  public FlowEdge(int v, int w, double capacity) {
    this.v = v;
    this.w = w;
    this.capacity = capacity;
    this.flow = 0.0;
  }

  // getters
  public int from() {
    return v;
  }

  public int to() {
    return w;
  }

  public double capacity() {
    return capacity;
  }

  public double flow() {
    return flow;
  }

  public int other(int vertex) {
    if (vertex == v) {
      return w;
    } else if (vertex == w) {
      return v;
    } else {
      throw new RuntimeException("invalid vertex");
    }
  }

  public double residualCapacityTo(int vertex) {
    if (vertex == v) {
      return flow;
    } else if (vertex == w) {
      return capacity - flow;
    } else {
      throw new RuntimeException("invalid vertex");
    }
  }

  // setters
  public void addResidualFlowTo(int vertex, double delta) {
    if (vertex == v) {
      flow -= delta;
    } else if (vertex == w) {
      flow += delta;
    } else {
      throw new RuntimeException("invalid vertex");
    }
  }
}
