package code.niuke.com;

/**
 * 0-1背包问题
 * reference：https://www.jianshu.com/p/a66d5ce49df5
 */
public class MaxBag {
    static int n;           // 描述物品个数
    static int c;           // 描述背包容量
    static int[] value;     // 描述物品价值
    static int[] weight;    // 描述物品重量

    public static void main(String[] args) {
        // 初始赋值操作
        value = new int[]{1500, 3000, 2000};
        weight = new int[]{1, 4, 3};
        c = 4;
        n = 3;

        // 构造最优解的网格:3行4列
        //n种物品在背包容易c下的最大价值
        int[][] maxValue = new int[n][c];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c; j++) {
                maxValue[i][j] = 0;
            }
        }   // end for

        // 填充网格
        for (int i = 0; i < n; i++) {
            //第二层循环求出，在只拿一个物品的前提下，背包容积和最大价值之间的关系
            for (int j = 1; j <= c; j++) {
                //选择编号为0的物品
                if (i == 0) {
                    maxValue[i][j - 1] = (weight[i] <= j ? value[i] : 0);//实现上一个网格的初始化
                } else {
                    int topValue = maxValue[i - 1][j - 1];  // 上一个网格的值
                    int thisValue = (weight[i] <= j        // 当前商品的价值 + 剩余空间的价值
                            ? (weight[i] < j ? value[i] + maxValue[i - 1][j - weight[i]] : value[i])//此处反应了状态转移
                            : topValue);

                    // 返回 topValue和thisValue中较大的一个
                    maxValue[i][j - 1] = Math.max(topValue, thisValue);
                }   // end if
            }   // end inner for
        }   // end outer for

        // 打印结果二维数组maxValue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c; j++) {
                System.out.printf("%6d", maxValue[i][j]);
            }
            System.out.println();
        }
    }
}