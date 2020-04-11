package newcoder.com;

import java.util.Arrays;
import java.util.Scanner;

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

    /**
     * 薯队长在平时工作中需要经常跟数字打交道，某一天薯队长收到了一个满是数字的表格，薯队长注意到这些数字里边很多数字都包含1，
     * 比如101里边包含两个1，616里包含一个1。
     *
     * 请你设计一个程序帮薯队长计算任意一个正整数n(0<n<=2147483647)，从1到n（包括n）的所有整数数字里含有多少个1
     *
     * 从1到13（包括13）有13个数字，其中包含1的数字有1，10，11，12，13，这些数字里分别有1，1，2，1，1个1，
     * 所以从1到13（包括13）的整数数字中一共有1+1+2+1+1=6个1
     * @param n
     * @return
     */
    public static int sumNum(int n) {
        int result = 0;
        for (int i = 0; i <= n; i++) {
            result +=curNum(i);
        }
        return result;
    }
    public static int curNum(int n) {
        int num = 0;
        String value = String.valueOf(n);
        for (char c : value.toCharArray()) {
            if(c == "1".charAt(0)) {
                num++;
            }
        }
        return num;
    }

//    public static void main(String[] arg) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextLine()) {
//            int n = in.nextInt();
//            System.out.println(MainTest.sumNum(n));
//        }
//    }

    public static void main(String[] arg) {
//        //快排应用
//        int[] input = {1,3,2,5,8,7};
//        quickSortVar(input);

        //数的查找
        System.out.println("13: " + sumNum(13));
    }
}
