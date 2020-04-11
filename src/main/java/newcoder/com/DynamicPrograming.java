package newcoder.com;

public class DynamicPrograming {
    /**
     * 给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，再给一个总金额 amount，
     * 问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1
     * 算法函数的签名如下：
     * // coins 中是可选硬币面值，amount 是目标金额
     * int coinChange(int[] coins, int amount);
     *
     * 比如说 k = 3，面值分别为 1，2，5，总金额 amount = 11。那么最少需要 3 枚硬币凑出，即 11 = 5 + 5 + 1
     */
    public static int coinChange(int[] coins, int amount) {
        /**
         * 思路：变量是amount，状态方程是p[n] = min{1 + p[n-c],p[n]},p是所有情况的矩阵,初始化的p[n]可以各个极大值
         */
        if (amount < 0) {
            return -1;
        }
        int[] p = new int[amount + 1];
        //金额需要计算到amount本身，所有这里是<=
        for (int a = 0; a <= amount; a++) {
            //数组初始化
            p[a] = amount;

            if (a == 0) {
                p[a] = 0;
                continue;
            }
            if (isArrayContain(a, coins)) {
                p[a] = 1;
                continue;
            }
            for (int cI = 0; cI != coins.length; cI++) {
                //子问题无解跳过
                int diff = a - coins[cI];
                if (diff < 0) continue;
                p[a] = Math.min(p[a], 1 + p[diff]);
            }
        }
        return p[amount];
    }

    private static boolean isArrayContain(int diff, int[] array) {
        if (array == null) {
            return false;
        }
        int left = 0, right = array.length - 1;
        int center = (right + left) / 2;
        while (left <= right) {
            if (array[center] == diff) {
                return true;
            } else if (array[center] < diff) {
                left = center + 1;
            } else if (array[center] > diff) {
                right = center - 1;
            }
            center = (right + left) / 2;
        }
        return false;
    }

    private static boolean isArrayContainRecurrent(int diff, int[] array, int left, int right) {
        if (array == null || left > right) {
            return false;
        }
        int center = (right + left) / 2;
        if (array[center] > diff) {
            return isArrayContainRecurrent(diff, array, left, center - 1);
        } else if (array[center] < diff){
            return isArrayContainRecurrent(diff, array, center + 1, right);
        } else {
            return true;
        }
    }

    public static void main(String[] arg) {
        int[] coins = {1,2,5};
        System.out.println("res: " + DynamicPrograming.coinChange(coins, 11));
    }
}
