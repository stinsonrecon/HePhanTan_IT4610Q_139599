package com.hust.soict.tienlm198261.helper;

public class InsertionSort implements NumberSorter{
    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            // Di chuyển các phần tử của arr [0 ... i - 1], lớn hơn key
            // đến một vị trí trước vị trí hiện tại của chúng
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // In các phần tử của mảng
    public void printArray(int[] arr) {
        int n = arr.length;
        for (int j : arr) System.out.print(j + " ");

        System.out.println();
    }
}
