package com.mumvall.national_exam_fe8.p2010h;

import java.util.Arrays;

public class Q2010h22h_fe_pm_qs_08 {
	private static int[] list = {5,7,2,3,8,1};
	private static int[] slist1 = new int[20];
	private static int[] slist2 = new int[20];

	public static void main(String[] args) {

		sort(list, list.length);

		System.out.println(Arrays.toString(list));
	}

	/**
	 * ソートするメソッド。
	 * @param list ソート対象のデータ保持配列
	 * @param num データの個数
	 */
	private static void sort(int[] list, int num) {
		int i = 0;
		int num1 = 0;
		int num2 = 0;

		if (num > 1 /** a */) {
			num1 = num / 2;
			num2 = num - num1;

			// 右側へ転記
			for(i = 0; i < num1; i++) {
				slist1[i] = list[i];
			}

			// 左側へ転記
			for(i = 0; i < num2; i++) {
				slist2[i] = list[num1 + i]/** b */;
			}

			// 右側の配列
			sort(slist1, num1);
			System.out.println(Arrays.toString(list));

			// 左側の配列
			sort(slist2, num2);
			System.out.println(Arrays.toString(list));
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

		while ((i < num1) && (j < num2)/** c */) {
			if (slist1[i] < slist2[i]) {
				list[i+j] = slist1[i];
				i++;
			} else {
				list[i + j] = slist2[j];
				j++;
			}
		}

		while ((i < num1)  || (j < num2)) {
			if(i < num1) {
				list[i+j] = slist1[i];
				i++;
			} else {
				list[i+j] = slist2[j];
				j++;
			}

		}

	}

}
