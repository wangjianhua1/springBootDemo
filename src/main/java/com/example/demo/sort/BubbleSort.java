package com.example.demo.sort;

/**
 * 不稳定算法：排序过程中可以改变2个相同值得位置，或者对象的index的排序即为不稳定排序
 */
public class BubbleSort {
    public static void swap(int A[], int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /**
     * 冒泡排序方法
     * 分类 -------------- 内部比较排序
     * 数据结构 ---------- 数组
     * 最差时间复杂度 ---- O(n^2)
     * 最优时间复杂度 ---- 如果能在内部循环第一次运行时,使用一个旗标来表示有无需要交换的可能,可以把最优时间复杂度降低到O(n)
     * 平均时间复杂度 ---- O(n^2)
     * 所需辅助空间 ------ O(1)
     * 稳定性 ------------ 稳定
     *
     * @param A
     */
    public static void BubbleSort(int[] A) {
        for (int j = 0; j < A.length; j++) {//只做一个循环排序
            for (int i = 0; i < A.length - 1; i++) {//冒泡，2个值作比较并且交换
                if (A[i] > A[i + 1]) {//mark  如果条件改成A[i] >= A[i + 1],则变为不稳定的排序算法
                    swap(A, i, i + 1);
                }
            }
        }
    }

    /**
     * 鸡尾酒冒泡排序
     * 分类 -------------- 内部比较排序
     * 数据结构 ---------- 数组
     * 最差时间复杂度 ---- O(n^2)
     * 最优时间复杂度 ---- 如果序列在一开始已经大部分排序过的话,会接近O(n)
     * 平均时间复杂度 ---- O(n^2)
     * 所需辅助空间 ------ O(1)
     * 稳定性 ------------ 稳定
     */
    public static void CocktailSort(int[] A) {
        int left = 0;
        int right = A.length - 1;
        while (left < right) {
            for (int i = left; i < right; i++) {
                if (A[i] > A[i + 1]) {
                    swap(A, i, i + 1);
                }
            }
            for (int i = right; i > left; i--) {
                if (A[i - 1] > A[i]) {
                    swap(A, i - 1, i);
                }
            }
            right--;
            left++;
        }
    }

    /**
     * 选择排序
     * 分类 -------------- 内部比较排序
     * 数据结构 ---------- 数组
     * 最差时间复杂度 ---- O(n^2)
     * 最优时间复杂度 ---- O(n^2)
     * 平均时间复杂度 ---- O(n^2)
     * 所需辅助空间 ------ O(1)
     * 稳定性 ------------ 不稳定
     *
     * @param A
     */
    public static void SelectionSort(int[] A) {
        for (int i = 0; i < A.length; i++) {
            int min = i;
            for (int k = i + 1; k < A.length; k++) {
                if (A[min] > A[k]) {
                    min = k;
                }
            }
            if (min != i) {
                swap(A, min, i);
            }
        }
    }

    /**
     * 插入排序
     * @param A
     */
    public static void InsertionSort(int A[]){
        int[] get=A;
        for (int i=0;i<A.length;i++){
            int temp=A[i];


        }



    }

    public static void main(String[] args) {
        int A[] = {6, 5, 3, 1, 5, 8, 7, 2, 4};
//        BubbleSort(A);
//        CocktailSort(A);
        SelectionSort(A);

        System.out.print("从小到大冒泡排序结果：");
        for (int i : A) {
            System.out.printf("%n" + i);
        }
    }

}
