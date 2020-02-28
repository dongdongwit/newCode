package code.niuke.com;
import java.util.Arrays;

/**
 * 常用排序算法的实现方案
 */
public class sortTest {
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

    public int partition(int []array,int lo,int hi){
        //固定的切分方式
        int key=array[lo];
        while(lo<hi){
            while(array[hi] >= key && hi > lo){//从后半部分向前扫描
                hi--;
            }
            //后半部分小于key的值放在左边
            array[lo]=array[hi];
            while(array[lo] <= key && hi > lo){//从前半部分向后扫描
                lo++;
            }
            //前半部分大于key的值放在右边
            array[hi]=array[lo];
        }
        //此时hi已完成自减，hi左边的值都小于key，hi右边的值都大于key
        array[hi]=key;
        return hi;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] array = { 37, 47, 23, 100, 19, 56, 56, 99, 9 };
        sortTest st = new sortTest();
        // st.bubbleSort(array);
        // st.selectSort(array);
        // st.insertionSort(array);
        st.quickSort(array, 0, array.length - 1);
        System.out.println("排序后：" + Arrays.toString(array));
    }
}

