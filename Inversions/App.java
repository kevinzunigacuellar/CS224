package Inversions;

public class App {

  // object to store number of inversions
  public static class WrapInt {
    int value;
  }

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
        System.out.print("Comparing " + left_arr[left_index] + " and " + right_arr[right_index]);
        print_array(arr);
        // calculate the inversions by substracting the original array indexes
        // i.e 3 -> with an index of 1 and 9 -> with an index of 4
        // then the number of inversions is 4 - 1 = 3
        // in this case I am modifiying the original array
        // so my original array indexes are changed so I dont get the right amount of
        // inversions
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
    int arr[] = { 7, 3, 11, 12, 9, 6, 2, 10, 8, 4, 5, 1 };
    int arr2[] = { 7, 3, 11, 12, 9, 6, 2, 10, 8, 4, 5, 1 };

    // 4+2+1+1 = 5
    WrapInt inv = new WrapInt();

    // compute
    print_array(arr);
    merge_sort(arr2, 0, arr.length - 1, inv);
    System.out.print("Sorted array: ");
    print_array(arr);
    System.out.println("Inversion count is " + inv.value);
  }
}
