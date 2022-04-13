package Knapsack;

import java.util.*;

class Knapsack {

  // Knapsack: method to find the maximum value that can be put in the knapsack
  static int knapSack(int W, int wi[], int vi[], int n) {
    int i, w;
    int M[][] = new int[n + 1][W + 1];

    // sort by weight before starting

    // fill M with -1
    for (int[] row : M) {
      Arrays.fill(row, -1);
    }

    for (i = 0; i <= n; i++) {
      System.out.println("Memoization table, Row " + i + " completed");
      for (w = 0; w <= W; w++) {
        if (i == 0 || w == 0)
          M[i][w] = 0;
        else if (wi[i - 1] <= w)
          M[i][w] = max(vi[i - 1] + M[i - 1][w - wi[i - 1]], M[i - 1][w]);
        else
          M[i][w] = M[i - 1][w];
      }
      print_table(M, n, W);
    }
    System.out.println("Knapsack with weight capacity " + W + " has optimal value: " + M[n][W]);
    System.out.println("___Knapsack Contains___");
    while (n != 0) {
      if (M[n][W] != M[n - 1][W]) {
        System.out.println("Item " + n + " (Value = " + vi[n - 1] + " Weight = " + wi[n - 1] + ")");
        W = W - wi[n - 1];
      }

      n--;
    }

    return M[n][W];
  }

  // helper function to print memoization table
  static void print_table(int M[][], int n, int W) {
    System.out.println("0 1 2 3 4 5 6");
    System.out.println("-------------------");
    for (int r = 0; r <= n; r++) {
      System.out.print("{");
      for (int i = 0; i <= r; i++) {
        System.out.print(" ");
        if (i == 0) {
          continue;
        } else {
          System.out.print(i);
          System.out.print(" ");
        }
      }
      System.out.print("} ");
      ;
      for (int c = 0; c <= W; c++) {
        System.out.print(M[r][c] + " ");
      }
      System.out.println();
    }
  }

  // helper method that returns the max value
  static int max(int a, int b) {
    return (a > b) ? a : b;
  }

  // Main function
  public static void main(String args[]) {
    int vi[] = new int[] { 6, 18, 2 };
    int wi[] = new int[] { 5, 2, 1 };
    int W = 6;
    int n = vi.length;
    knapSack(W, wi, vi, n);
  }
}
