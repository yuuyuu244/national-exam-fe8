/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mumvall.national_exam_fe8.p2018h;

/**
 *
 * @author user
 */
public class Q2018h30h_fe_pm_qs_08 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //int[] data = {0, 10, 20, 30, 40, 50, 60};
    	int[] data = {60, 30, 45, 15, 5, 10, 20};
        //int[] heap = {0, 10, 20, 30, 40, 50, 60};
        int[] heap = new int[data.length];
        int hnum = data.length;

        printHeap(data, hnum);
        //makeHeap(data, heap, data.length);
        heapSort(data, heap, data.length);
        // ヒープ後
        System.out.println("-------- ヒープ作成後 ----------");
        printHeap(heap, heap.length);
    }

    /**
     * ヒープを作成する.
     * @param data データが格納されている1次元配列
     * @param heap ヒープの性質を満たすようにデータを格納する1次元配列
     * @param hnum データの個数
     */
    public static void makeHeap(int[] data, int[] heap, int hnum) {
        int i;
        int k;

        for(i = 0; i < hnum; i++) {
            heap[i] = data[i];
            k = i;

            while(k > 0) {
                if(heap[k] > heap[parent(k)]) {
                    swap(heap, k, parent(k));
                    k = parent(k);
                } else {
                    break;
                }

            }
        }
    }

    /**
     * データを交換する.
     *
     * @param heap 対象配列
     * @param i 1つ目の添え字
     * @param k 2つ目の添え字
     */
    public static void swap(int[] heap, int i, int k) {
        int tmp;
        tmp = heap[i];
        heap[i] = heap[k];
        heap[k] = tmp;
    }

    /**
     * 左,子要素の添え字を求める.
     * @param i 添え字
     * @return 左子要素の添え字
     */
    public static int lchild(int i) {
        return 2 * i + 1;
    }

    /**
     * 右,子要素の添え字を求める.
     * @param i
     * @return
     */
    public static int rchild(int i) {
        return 2 * i + 2;
    }

    /**
     * 親の添え字を求める.
     * @param i
     * @return
     */
    public static int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * 表示するメソッド.
     * @param heap
     * @param hnum
     */
    public static void printHeap(int[] heap, int hnum) {
    	System.out.println("");
        if(0 < hnum) {
            System.out.println("            " + heap[0]);
            if(2 < hnum) {
                System.out.println("        " + heap[1] +"        " + heap[2]);
                if(6 < hnum) {
                    System.out.println("    " + heap[3] + "    " + heap[4] + "    " + heap[5] + "    " + heap[6]);
                }
            }

        }

    }

    //--------------- プログラム2 ------------------------
    private static void heapSort(int[] data, int[] heap, int hnum) {
    	makeHeap(data, heap, hnum);
    	for(int last = hnum - 1; last > 0; last--) {
    		swap(heap, 0, last);
    		downHeap(heap, last - 1);
    		printHeap(heap,heap.length);
    	}
    }

    /**
     * ヒープの性質を保つように並べ替える
     * @param heap
     * @param hlast
     */
	private static void downHeap(int[] heap, int hlast) {
		// TODO 自動生成されたメソッド・スタブ
		int n = 0;
		int tmp;

		while(lchild(n) <= hlast) {
			tmp = lchild(n);
			if(rchild(n) <= hlast) {
				if(heap[tmp] <= heap[rchild(n)]) {
					tmp = rchild(n);
				}

			}

			if(heap[tmp] > heap[n]) {
				swap(heap, n, tmp);
			} else {
				return;
			}

			n = tmp;
		}
	}
}
