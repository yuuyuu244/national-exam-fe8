package com.mumvall.national_exam_fe8.p2019h;

import java.util.Arrays;

/**
 * 2019春 アルゴリズムの問題
 * @author mumvall
 *
 */
public class Q2019h31h_fe_pm_8 {
	/** 配列の要素数 */
	public static final int ARRAY_SIZE = 7;

	/** 葉の数 */
	public static Integer size = 4;

	/** 親が作成されていない要素の数 */
	private static int nsize = 0;


	/**
	 * mainメソッド(独自)
	 * @param args コマンドライン引数
	 */
	public static void main(String[] args) {
		int[] parent = new int[ARRAY_SIZE];
		int[] left = new int[ARRAY_SIZE];
		int[] right = new int[ARRAY_SIZE];
		// 本試問題の与えられた数値そのまま
		int[] freq = { 10, 2, 4, 3, -1, -1, -1 };

		// 初期化
		init(parent, left, right);

		// ハフマン木作成
		huffman(size, parent, left, right, freq);

		System.out.println("-------ハフマン木作成後の状態-------");

		// 変更後の表示
		printAry("parent", parent);
		printAry("left", left);
		printAry("right", right);
		printAry("freq", freq);

		// チェック対象の添え字
		int index = 1;

		System.out.println("要素" + index + "\t: " + encode(index, parent, left));

	}

	/**
	 * 配列表示メソッド(独自)
	 * @param text 表示名
	 * @param array 配列
	 */
	static void printAry(String text, int[] array) {
		System.out.println(text + "\t" + Arrays.toString(array));
	}

	/**
	 * 初期化メソッド(独自)
	 * @param parent 配列
	 * @param left 配列
	 * @param right 配列
	 */
	public static void init(int[] parent, int[] left, int[] right) {
		Arrays.fill(parent, -1); // parent: -1で初期化
		Arrays.fill(left, -1); // left  : -1で初期化
		Arrays.fill(right, -1); // right : -1で初期化

	}

	/**
	 * プログラム1
	 * ハフマン木作成アルゴリズム
	 *
	 * @param size 		葉である節の個数
	 * @param parent 	初期化(-1)された配列
	 *                  子から見て親の要素番号
	 * @param left 		初期化(-1)された配列
	 *                  親から見て左側の要素番号
	 * @param right		初期化(-1)された配列
	 *                  親から見て右側の要素番号
	 * @param freq		文字の出現回数が格納された配列
	 */
	private static void huffman(int size, int[] parent, int[] left,
			int[] right, int[] freq) {
		// i:左要素の添え字, j:右要素の添え字
		int i, j;
		// 親が作成されていない要素の要素番号を保持する配列
		int[] node = new int[7];

		sortNode(size, parent, freq, nsize, node);

		// 親が作成されていない要素が2つ以上の場合ループする。
		// つまり親が作成されていないノードが1つ(ルートノードのみ)
		// ならハフマン木が作成完了として、ループを抜ける。
		while (nsize >= 2 /* c */) {
			// 一番小さい出現回数の要素番号を取り出す。
			i = node[0];
			// 二番目に小さい出現回数の要素番号を取り出す。
			j = node[1];

			// 親要素作成に当たり、左右の要素を決定している。
			left[size] = i;
			right[size] = j;

			// ハフマン木の子要素(左右)の合計 = 親の要素
			// を満たすために、足し算する。
			freq[size] = freq[i] + freq[j];

			// 子要素視点で親要素を決定している。
			parent[i] = size;
			parent[j] = size;

			// 親を作成したので, 範囲を1つ増やすためのインクリメント
			size++;

			sortNode(size, parent, freq, nsize, node);
		}
	}

	/**
	 * 親が作成されていないノードの数を探して、
	 * <br>出現回数でソートをかけnode[]にその順番で要素番号(index)を
	 * <br>格納する。
	 *
	 * @param size 節の数(はじめは葉ノードの数でやってくる。そのあとは、1ずつインクリメントされてやってくる。)
	 * @param parent 親ノードの配列
	 * @param freq 出現回数の配列
	 * @param nsize_ "整列対象とした節の個数"らしい。
	 *                (親が作成されていない要素の数)
	 *                正直初期化するので必要なのかわからない
	 * @param node 戻り値にしない代わりにポインタを渡して格納させるつもりなのかも?
	 *              もう少し良い作り方をすれば、わざわざ引数で渡す必要もない。
	 */
	private static void sortNode(int size, int[] parent, int[] freq,
			int nsize_, int[] node) {
		nsize_ = 0;

		// 親が作成されていない要素の数を求めてnsizeに格納
		for (int i = 0; i < size; i++) {
			if (parent[i] < 0 /* d */) {
				// 親が作成されていない要素番号のみnode[]に格納する。
				node[nsize_] = i;
				// 親が作成されていない要素番号の作成
				nsize_++;
			}
		}

		sort(freq, nsize_, node);
		nsize = nsize_;
	}

	/**
	 * 昇順ソートプログラム
	 *
	 * <br>node[]には親が作成されていない要素の要素番号が格納されているため、
	 * <br>そのnode[]に対応する要素の値をfreq[]から取り出して、
	 * <br>その値で昇順に並べ替える(freq[]自体はそのまま)
	 * <br>そして、並べ変えた要素の順に要素番号をnode[]に格納する。
	 *
	 * @param freq 節の値が格納された配列
	 * @param nsize node中の整列対象の節の個数
	 *              (親が作成されていない要素の数)
	 * @param node 節が表す要素組の要素番号を格納した配列
	 */
	private static void sort(int[] freq, int nsize, int[] node) {
		// temp用の配列freq_copy[]を用意
		int[] freq_copy = new int[ARRAY_SIZE];

		// 親の作成されていない要素配列番号の出現回数のコピー
		for (int i = 0; (node[i] != -1) && (i < nsize); i++) {
			freq_copy[i] = freq[node[i]];
		}

		//昇順ソート
		// バブルソートを採用(問題自体でそーとアルゴリズムの指定はないため、任意)
		for (int i = 0; i < nsize - 1; i++) {
			for (int j = 0; j < nsize - i - 1; j++) {
				if (freq_copy[j] > freq_copy[j + 1]/*freq[j] > freq[j+1]*/) {
					int asc = freq_copy[j];
					int asc2 = node[j];

					freq_copy[j] = freq_copy[j + 1];
					node[j] = node[j + 1];

					freq_copy[j + 1] = asc;
					node[j + 1] = asc2;
				}
			}
		}

	}

	//--------------------------------------------------------

	/**
	 *  プログラム2
	 *  <br>作成したハフマン木から、ビット列を取り出す作業。
	 *  <br>本試の問題では、0,1を表示しているが、
	 *  <br>いけてないので文字列で返す。
	 */
	private static String encode(int k, int[] parent, int[] left) {
		StringBuilder s = new StringBuilder();
		// 親が-1でない場合。
		// つまり、ルートノード(根)ではない場合。
		// 一番トップにいるノード以外は実行するよっ！
		if (parent[k] >= 0 /* e */) {
			s.append(encode(parent[k], parent, left));
			// 親ノードから見て自分が右、左どっちにいるのか
			// を確認するための条件
			if (left[parent[k]] == k /* f */) {
				// 左の時
				s.append("0");
				//System.out.print("0");
			} else {
				// 右の時
				s.append("1");
				//System.out.print("1");
			}

		} /*else {
			System.out.println("根まで戻った" + s.toString());
			}*/
		return s.toString();

	}

}