import java.util.*;

public class RecursiveStuff {
    public static int fibonacci(int i) {
        if (i <= 1) {
            return 1;
        }
        if (i == 2) {
            return 1;
        }
        return fibonacci(i - 1) + fibonacci(i - 2);
    }

    public static void printOneByOne(String s) {
        System.out.println(s);
        if (s.length() > 1) {
            printOneByOne(s.substring(1));
        }
    }

    public static void printArray(int[] arr, int i) {
        System.out.println(arr[i]);
        if (i < arr.length - 1) {
            printArray(arr, i + 1);
        }
    }

    public static void printArray(ArrayList<Integer> ints, int i) {
        System.out.println(ints.get(i));
        if (i < ints.size() - 1) {
            printArray(ints, i + 1);
        }
    }

    public static int recursiveBinarySearch(int[] array, int start, int end, int target) {
        int middle = (start + end) / 2;
        if (target == array[middle])
            return middle;

        if (end < start)
            return -1;

        if (target < array[middle])
            return recursiveBinarySearch(array, start, middle - 1, target);

        if (target > array[middle])
            return recursiveBinarySearch(array, middle + 1, end, target);

        return -1;
    }

    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        mergeSortHelper(arr, 0, arr.length - 1, temp);
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

    private static void mergeSortHelper(int[] arr, int from, int to, int[] temp) {
        if (from < to) {
            int middle = (from + to) / 2;
            mergeSortHelper(arr, from, middle, temp);
            mergeSortHelper(arr, middle + 1, to, temp);
            merge(arr, from, middle, to, temp);
        }
    }

    private static void merge(int[] arr, int from, int mid, int to, int[] temp) {
        int i = from;
        int j = mid + 1;
        int k = from;

        while (i <= mid && j <= to) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = arr[i];
            i++;
            k++;
        }

        while (j <= to) {
            temp[k] = arr[j];
            j++;
            k++;
        }

        for (k = from; k <= to; k++) {
            arr[k] = temp[k];
        }
    }
    public static void mergeSort(ArrayList<Integer> arr) {
        int[] temp = new int[arr.size()];
        mergeSortHelper(arr, 0, arr.size() - 1, temp);
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }
    private static void mergeSortHelper(ArrayList<Integer> arr, int from, int to, int[] temp) {
        if (from < to) {
            int middle = (from + to) / 2;
            mergeSortHelper(arr, from, middle, temp);
            mergeSortHelper(arr, middle + 1, to, temp);
            merge(arr, from, middle, to, temp);
        }
    }
    private static void merge(ArrayList<Integer> arr, int from, int mid, int to, int[] temp) {
        int i = from;
        int j = mid + 1;
        int k = from;

        while (i <= mid && j <= to) {
            if (arr.get(i) < arr.get(j)) {
                temp[k] = arr.get(i);
                i++;
            } else {
                temp[k] = arr.get(j);
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = arr.get(i);
            i++;
            k++;
        }

        while (j <= to) {
            temp[k] = arr.get(j);
            j++;
            k++;
        }

        for (k = from; k <= to; k++) {
            arr.set(k, temp[k]);
        }
    }
}
