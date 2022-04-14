
import java.io.*;
import java.util.*;

class Knapsack {

  // Knapsack: method to find the maximum value that can be put in the knapsack
  static int knapSack(int W, int wi[], int vi[], int n) {
    int i, w;
    int M[][] = new int[n + 1][W + 1];

    // fill M with -1
    for (int[] row : M) {
      Arrays.fill(row, -1);
    }

    // print header
    System.out.println("Solving knapsack weight capacity " + W + ", with " + n + " items");
    System.out.println("============================================================");

    // knapsack algorithm bottom up
    for (i = 0; i <= n; i++) {
      System.out.println("Memoization table, Row " + i + " completed \n");
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
    // return items that are in the knapsack
    print_items(M, n, W, wi, vi);

    return M[n][W];
  }

  // helper method to print the items in the knapsack
  static void print_items(int M[][], int n, int W, int wi[], int vi[]) {
    System.out.println("Knapsack with weight capacity " + W + " has optimal value: " + M[n][W] + "\n");
    System.out.println("________Knapsack Contains________");
    while (n != 0) {
      if (M[n][W] != M[n - 1][W]) {
        System.out.printf("Item " + n + " (Value = %2d, Weight = %2d) \n", vi[n - 1], wi[n - 1]);
        W = W - wi[n - 1];
      }
      n--;
    }
  }

  // helper function to print memoization table
  static void print_table(int M[][], int n, int W) {
    int arr[] = new int[W + 1];
    System.out.printf("%10s", " ");
    for (int i = 0; i <= W; i++) {
      arr[i] = i;
      System.out.printf("%5d", arr[i]);
    }
    System.out.println();
    System.out.println("-----------------------------------------------------");
    for (int r = 0; r <= n; r++) {
      String strg = "{";
      for (int i = 0; i <= r; i++) {
        if (i == 0) {
          continue;
        } else if (i == r) {
          strg += i;
        } else {
          strg += i + ",";
        }
      }
      strg = strg + "}";
      System.out.printf("%7s | ", strg);

      for (int c = 0; c <= W; c++) {
        System.out.printf("%5d", M[r][c]);
      }
      System.out.println();
    }
    System.out.println("\n \n");

  }

  // helper function that returns the max value
  static int max(int a, int b) {
    return (a > b) ? a : b;
  }

  // Main function
  public static void main(String args[]) {
    // initialize variables
    int vi[] = new int[3];
    int wi[] = new int[3];
    int W;
    int n = vi.length;

    // read file
    try {
      File myInput = new File("sample_input.txt");
      Scanner s = new Scanner(myInput);
      W = s.nextInt();
      while (s.hasNextInt()) {
        int index = s.nextInt();
        vi[index - 1] = s.nextInt();
        wi[index - 1] = s.nextInt();
      }
      s.close();

      // call knapsack algorithm
      knapSack(W, wi, vi, n);

      // catch exceptions
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
