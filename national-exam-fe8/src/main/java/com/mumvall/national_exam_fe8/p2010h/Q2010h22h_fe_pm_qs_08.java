package com.mumvall.national_exam_fe8.p2010h;

import java.util.Arrays;

public class Q2010h22h_fe_pm_qs_08 {

	private static int ITEM_NUM = 7;

	public static void main(String[] args) {
		q1();
		System.out.println("--------------------------");
		q2();

	}

	private static void q1() {
		int[] list = {5,7,4,2,3,8,1};
		sort(list, list.length);

		System.out.println(Arrays.toString(list));
	}

	private static void q2() {
		int[] list = {3,8,2,7,5,1};
		sort(list, list.length);

		System.out.println(Arrays.toString(list));
	}

	/**
	 * ソートするメソッド。
	 * @param list ソート対象のデータ保持配列
	 * @param num データの個数
	 */
	private static void sort(int[] list, int num) {
		int[] slist1 = new int[ITEM_NUM];
		int[] slist2 = new int[ITEM_NUM];

		int i = 0;
		int num1 = 0;
		int num2 = 0;

		if (num > 1 /** a */) {
			// 左側の個数
			num1 = num / 2;
			// 右側の個数
			num2 = num - num1;

			// 左側へ転記
			for(i = 0; i < num1; i++) {
				slist1[i] = list[i];
			}

			// 右側へ転記
			for(i = 0; i < num2; i++) {
				slist2[i] = list[num1 + i]/** b */;
			}

			// 左側の配列
			System.out.println("[LEFT ]: "+Arrays.toString(slist1));
			// 左側の配列をマージソートにかける
			sort(slist1, num1);
			System.out.println("[LEFT ]: "+Arrays.toString(slist1));

			// 右側の配列
			System.out.println("[RIGHT]: "+Arrays.toString(slist2));
			// 右側の配列をマージソートにかける
			sort(slist2, num2);
			System.out.println("[RIGHT]: "+Arrays.toString(slist2));

			// マージしつつソートも行う。
			merge(slist1, num1, slist2, num2, list);
		}


	}

	/**
	 * マージ(統合)する。
	 * @param slist1
	 * @param num1
	 * @param slist2
	 * @param num2
	 * @param list
	 */
	private static void merge(int[] slist1, int num1,
								int[] slist2, int num2,
								int[] list) {
		int i = 0;
		int j = 0;

		// 左右の配列どちらのチェックも両方終了したら終了
		while ((i < num1) && (j < num2)/** c */) {

			// 左側の配列の要素 < 右側の配列の要素
			if (slist1[i] < slist2[j]) {
				// 左側の配列の要素のほうが小さかったら...
				// 左側の配列を転記
				list[i + j] = slist1[i];
				i++;
			} else {
				// 右側の配列の要素のほうが小さかったら(=も含む)...
				// 右側の配列を転記
				list[i + j] = slist2[j];
				j++;
			}
		}

		// どちらかの要素がまだ転記済みでない間ループさせる。
		// (全て要素が転記終了したら終了する。)
		while ((i < num1)  || (j < num2)) {
			if(i < num1) {
				// 左側の配列を転記
				list[i+j] = slist1[i];
				i++;
			} else {
				// 右側の配列を転記
				list[i+j] = slist2[j];
				j++;
			}

		}

	}

}
