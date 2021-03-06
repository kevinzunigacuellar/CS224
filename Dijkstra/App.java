package Dijkstra;

import Dijkstra.utils.Graph;

/*
  * Note:
  * Node s has been replaced by 1
  * Node t has been replaced by 8
*/

public class App {
  public static void main(String[] args) {
    Graph g = new Graph(9);
    g.addEgde(1, 2, 9);
    g.addEgde(1, 4, 14);
    g.addEgde(1, 3, 15);
    g.addEgde(2, 7, 23);
    g.addEgde(3, 5, 20);
    g.addEgde(3, 8, 44);
    g.addEgde(4, 3, 5);
    g.addEgde(4, 5, 30);
    g.addEgde(4, 7, 18);
    g.addEgde(5, 6, 11);
    g.addEgde(5, 8, 16);
    g.addEgde(6, 7, 6);
    g.addEgde(6, 8, 6);
    g.addEgde(7, 8, 19);
    g.addEgde(7, 5, 2);

    int[] distance = g.dijkstra(g, 1);
  }

}
