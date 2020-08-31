package newcoder.com;

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

    public static double orgSqrt(double c)
    {
        if (c < 0) return Double.NaN;
        double err = 1e-15;
        double t = c;
        while (Math.abs(t - c/t) > err * t)
            t = (c/t + t) / 2.0;
        return t;
    }

    public static double sqrt(double input) {
        double mid = Double.NaN;
        if (input >= 0) {
            double low = input > 1 ? 1 : input, high = input > 1 ? input : 1;
            mid = (low + high) / 2;
            while (Math.abs(mid*mid - input) > 1e-15) {
//                大了,最大值减小
                if ((mid*mid - input) > 0) {
                    high = mid;
                } else {
                    low = mid;
                }
                mid = (low + high) / 2;
            }
        }
        return mid;
    }

    public static void main(String[] arg) {
        System.out.println(String.format("result: %s", sqrt(16)));
        System.out.println(String.format("result: %s", sqrt(1)));
        System.out.println(String.format("result: %s", sqrt(0.25)));
        System.out.println(String.format("result: %s", sqrt(0)));
        System.out.println(String.format("result: %s", sqrt(-4)));
//        int[] arr = {1,3,5,7,9,11,23,44,66};
//        String s = "asb";
//        s
//        System.out.println("result: " + recursionBinarySearch(arr, 11, 0, arr.length - 1));
//        System.out.println("result: " + binarySearch(arr, 11, 0, arr.length - 1));
    }
}
