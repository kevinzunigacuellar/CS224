package Inversions;

public class App {

  // object to store number of inversions (pass by reference)
  public static class WrapInt {
    int value;
  }

  // merge method
  public static void merge(int arr[], int left, int mid, int right, WrapInt inv) {

    // calculate the size of the left and right subarrays
    int left_size = mid - left + 1;
    int right_size = right - mid;

    // create and populate the left and right subarrays
    int left_arr[] = new int[left_size];
    int right_arr[] = new int[right_size];

    for (int i = 0; i < left_size; i++) {
      left_arr[i] = arr[left + i];
    }

    for (int i = 0; i < right_size; i++) {
      right_arr[i] = arr[mid + 1 + i];
    }
    // merge the left and right subarrays
    int left_index = 0;
    int right_index = 0;
    int arr_index = left;

    while (left_index < left_size && right_index < right_size) {

      if (left_arr[left_index] <= right_arr[right_index]) {
        arr[arr_index] = left_arr[left_index];
        left_index++;
      } else {
        arr[arr_index] = right_arr[right_index];
        right_index++;
        inv.value += left_size - left_index;
      }
      arr_index++;
    }

    // copy the remaining elements of the left subarray
    while (left_index < left_size) {
      arr[arr_index] = left_arr[left_index];
      left_index++;
      arr_index++;
    }

    // copy the remaining elements of the right subarray
    while (right_index < right_size) {
      arr[arr_index] = right_arr[right_index];
      right_index++;
      arr_index++;
    }
  }

  // merge sort
  public static void merge_sort(int arr[], int left, int right, WrapInt inv) {
    if (left < right) {
      int mid = (left + right) / 2;
      merge_sort(arr, left, mid, inv);
      merge_sort(arr, mid + 1, right, inv);
      merge(arr, left, mid, right, inv);
    }
  }

  // print array method
  public static void print_array(int arr[]) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // variables
    int arr[] = { 1, 13, 14, 2, 25, 26 };
    WrapInt inv = new WrapInt();

    // exercise 1
    System.out.print("Before sorting: ");
    print_array(arr);
    merge_sort(arr, 0, arr.length - 1, inv);
    System.out.print("Sorted array: ");
    print_array(arr);
    System.out.println("Inversion count is " + inv.value);

    // exercise 2
    int arr2[] = { 45, 23, 12, 34, 10 };
    WrapInt inv2 = new WrapInt();

    System.out.print("Before sorting: ");
    print_array(arr2);
    merge_sort(arr2, 0, arr2.length - 1, inv2);
    System.out.print("Sorted array: ");
    print_array(arr2);
    System.out.println("Inversion count is " + inv2.value);
  }
}
