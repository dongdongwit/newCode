package nowcoder.com;
import java.util.Arrays;

/**
 * 常用排序算法的实现方案
 */
public class SortTest {
    // 冒泡排序，：每个元素都和剩下方向的元素比较一次
    public void bubbleSort(int[] array) {
        int t = 0;
        for (int i = 0; i < array.length - 1; i++)
            for (int j = 0; j < array.length - 1 - i; j++)
                // 递增排序
                if (array[j] > array[j + 1]) {
                    t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
    }

    //归并排序：先对数组均分成两部分，递归直到子序列长度<=2;对子序列合并，最终完成结果的输出
    public void mergeSort(int[] data, int left, int right) {
        if (left >= right)
            return;
        // 找出中间索引
        int center = (left + right) / 2;
        // 对左边数组进行递归
        mergeSort(data, left, center);
        // 对右边数组进行递归
        mergeSort(data, center + 1, right);
        // 合并
        merge(data, left, right);
    }

    private void merge(int[] data, int leftBeginId, int rightEndId) {
        if (leftBeginId >= rightEndId)
            return;
        // 找出中间索引
        int centerId = (leftBeginId + rightEndId) / 2;
        // 临时数组
        int[] result = new int[data.length];
        // 右数组第一个元素索引
        int rightId = centerId + 1;
        // third 记录临时数组的索引
        int leftId = leftBeginId;
        // 缓存左数组第一个元素的索引
        int resultId = leftBeginId;
        while (leftId <= centerId && rightId <= rightEndId) {
            // 从两个数组中取出最小的放入临时数组
            if (data[leftId] <= data[rightId]) {
                result[resultId++] = data[leftId++];
            } else {
                result[resultId++] = data[rightId++];
            }
        }
        // 剩余部分依次放入临时数组（实际上两个while只会执行其中一个）
        while (rightId <= rightEndId) {
            result[resultId++] = data[rightId++];
        }
        while (leftId <= centerId) {
            result[resultId++] = data[leftId++];
        }
        // 将临时数组中的内容拷贝回原数组中
        // （原left-right范围的内容被复制回原数组）
        while (leftBeginId <= rightEndId) {
            data[leftBeginId] = result[leftBeginId++];
        }
    }

    // 选择排序
    public void selectSort(int[] array) {
        int t = 0;
        for (int i = 0; i < array.length - 1; i++){
            int index=i;
            for (int j = i + 1; j < array.length; j++) {
                //通过for循环找到比较元素中最大的值的索引
                if (array[index] > array[j]) {
                    index=j;
                }
            }
            if(index!=i){ //找到了比array[i]小的则与array[i]交换位置
                t = array[i];
                array[i] = array[index];
                array[index] = t;
            }
        }
    }

    public void insertionSort(int[] array) {
        int i, j, t = 0;
        for (i = 1; i < array.length; i++) {
            if(array[i]<array[i-1]){
                t = array[i];
                for (j = i - 1; j >= 0 && t < array[j]; j--)
                    array[j + 1] = array[j];
                //插入array[i]
                array[j + 1] = t;
            }
        }
    }

    // 分治法快速排序
    public void quickSort(int[] array, int low, int high) {// 传入low=0，high=array.length-1;
        if (low >= high) {
            return;
        }
        int p_pos = partition(array, low, high);
        quickSort(array, low, p_pos - 1);// 排序左半部分
        quickSort(array, p_pos + 1, high);// 排序右半部分
    }

    /**
     * 固定切分点key，调整array值的顺序，使得key都大于左边的值小于右边的值--升序
     * @param array
     * @param lo
     * @param hi
     * @return
     */
    private int partition(int []array,int lo, int hi){
        //固定的切分方式
        int key = array[lo];
        while(lo < hi){
            while(array[hi] >= key && hi > lo){//从后半部分向前扫描
                hi--;
            }
            //后半部分小于key的值放在左边
            array[lo] = array[hi];
            while(array[lo] <= key && hi > lo){//从前半部分向后扫描
                lo++;
            }
            //前半部分大于key的值放在右边
            array[hi] = array[lo];
        }
        //返回key所在位置的坐标
        array[lo] = key;
        return lo;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] array = { 37, 47, 23, 100, 19, 56, 56, 99, 9 };
        SortTest st = new SortTest();
        // st.bubbleSort(array);
        // st.selectSort(array);
        // st.insertionSort(array);
//        st.quickSort(array, 0, array.length - 1);
        System.out.println("排序前：" + Arrays.toString(array));
        st.mergeSort(array, 0, array.length - 1);
        System.out.println("排序后：" + Arrays.toString(array));
    }
}

