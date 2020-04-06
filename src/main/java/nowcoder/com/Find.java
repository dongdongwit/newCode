package nowcoder.com;

public class Find {
    public static int recursionBinarySearch(int[] arr, int key, int low, int high) {
        int mid = (low + high) / 2;
        if (arr[mid] == key) {
            return mid;
        }
        if (arr[mid] < key) {
            return recursionBinarySearch(arr, key, mid + 1, high);
        } else if (arr[mid] > key) {
            return recursionBinarySearch(arr, key, low, mid - 1);
        } else {
            return -1;
        }
    }

    public static int binarySearch(int[] arr, int key, int low, int high) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > key) {
                high = mid - 1;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else if (arr[mid] == key) {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] arg) {
        int[] arr = {1,3,5,7,9,11,23,44,66};
        String s = "asb";
//        s
//        System.out.println("result: " + recursionBinarySearch(arr, 11, 0, arr.length - 1));
        System.out.println("result: " + binarySearch(arr, 11, 0, arr.length - 1));
    }
}
