package com.sinllychen.string;
import java.util.Arrays;

/**
 * 列举数组中任意元素和为定值的组合
 */
public class SolveProb {
    // Arrays.sort(arr);
    static int[] flag = new int[100];
    static int index = 0;// 记录当前
    public SolveProb() {        
    }
    public static void numGroup(int[] arr, int start, int length, int sum) {
        if (sum == 0) {
            for (int j = 0; j < index; j++) {
                System.out.print(flag[j]);
            }
            System.out.println();
        } else {
            for (int i = start; i < length; i++) {
                flag[index++] = arr[i];
                SolveProb.numGroup(arr, i + 1, length-1, sum - arr[i]);
            }
        }
        index--;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 2, 4, 5, 6, 7, 8, 9 };
        Arrays.sort(arr);
        int sum = 7;
        SolveProb.numGroup(arr, 0, arr.length, sum);
    }
}