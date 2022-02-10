package com.zhaipz.study.datastructure.service.sort;

/**
 * @author zhaipz
 * @ClassName: Sort
 * @Description: 经典排序算法
 * @date 2022/1/10 11:46
 */
public class Sort {

    public static void main(String[] args) {

    }

    /**
    * @Title: 一、冒泡排序
    * @Description: 时间复杂度  O(n^2) 空间复杂度 O(1)
    */
    public int[] bubbleSort(int[] array){
        int n = array.length;
        for(int i = 0;i < n;i++){
            for(int j = 0;j < n-i-1;j++){
                if(array[j] < array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        return array;
    }

    /**
     * @Title: 二、选择排序
     * @Description: 时间复杂度  O(n^2) 空间复杂度 O(1)
     */
    public int[] selectSort(int[] array){
        int n = array.length;

        for(int i = 0;i < n-1;i++){
            int minIndex = i;
            for(int j = i+1;j < n;j++){
                if(array[j] < array[minIndex])minIndex = j;
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        return array;
    }

    /**
     * @Title: 三、插入排序
     * @Description: 时间复杂度  O(n^2) 空间复杂度 O(1)
     */
    public int[] insertSort(int[] array){
        int n = array.length;

        for(int i = 1;i < n;i++){
            if(array[i] < array[i-1]){
                int j = i-1;
                int x = array[i];
                array[i] = array[i-1];
                while(x < array[j]){
                    array[j+1] = array[j];
                    j--;
                }
                array[j+1] = x;
            }
        }
        return array;
    }

    /**
     * @Title: 四、快速排序
     * @Description: 时间复杂度  O(n*log2n) 空间复杂度 O(log2n)
     */
    public int[] quickSort(int[] array){
        quickSort(array,0,array.length-1);
        return array;
    }

    public void quickSort(int[] array,int l,int r){
        if(l >= r)return;
        int start = l;
        int end = r;
        int flag = array[start];

        while(start < end){
            while(start < end && array[end] >= flag)end--;
            if(start < end)array[start++] = array[end];

            while(start < end && array[start] <= flag)start++;
            if(start < end)array[end--] = array[start];
        }
        array[start] = flag;
        quickSort(array,l,start-1);
        quickSort(array,start+1,r);
    }

    /**
     * @Title: 五、归并排序
     * @Description: 时间复杂度  O(n*log2n) 空间复杂度 O(n)
     */
    public int[] mergeSort(int[] array){
        mergeSort(array,0,array.length-1);
        return array;
    }

    public void mergeSort(int[] array,int l,int r){
        if(l >= r)return;

        int mid = l + (r-l/2);

        mergeSort(array,l,mid);
        mergeSort(array,l,mid);
        merge(array,l,mid,r);
    }

    public void merge(int[] array,int l,int mid,int r){
        int[] temp = new int[r-l+1];

        int i = l;
        int j = mid+1;
        int k = 0;

        while(i <= mid && j <= r){
            if(array[i] < array[j]){
                temp[k++] = array[i++];
            }else{
                temp[k++] = array[j++];
            }
        }

        while(i <= mid){
            temp[k++] = array[i++];
        }
        while(j <= r){
            temp[k++] = array[j++];
        }

        for(int m = 0;m < temp.length;m++){
            array[l+m] = temp[m];
        }
    }
}
