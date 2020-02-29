package nowcoder.com;

import java.util.Arrays;

public class MainTest {
    /**
     * 乱序数组，要求奇数在前，偶数在后，保持原序，要求O(n)时间，O(1)空间
     * 1 3 2 5 8 7 => 1 3 5 7 2 8
     * @param input
     */
    public static void quickSortVar(int[] input) {
        System.out.println("before: " + Arrays.toString(input));
        int startId = 0;
        int endId = input.length - 1;
        while(startId < endId) {
            //找到偶数id
            while(input[startId] % 2 == 1 && startId < endId) {
                startId++;
            }
            //交换
            int tem = input[startId];
            input[startId] = input[endId];
            input[endId] = tem;
            //找到奇数
            while(input[endId] %2 == 0 && startId < endId) {
                endId--;
            }
            tem = input[startId];
            input[startId] = input[endId];
            input[endId] = tem;
        }
        System.out.println("after: " + Arrays.toString(input));
    }

    public static void main(String[] arg) {
        //快排应用
        int[] input = {1,3,2,5,8,7};
        quickSortVar(input);


    }
}
