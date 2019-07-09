package com.mumvall.national_exam_fe8.p2013a;

public class Q2013h25h_fe_pm_qs_08 {
	/* 購入情報 */
	/** 起点ポインター */
	private static int startPtr;

	/** 購入行数 */
	private static int buyLines;

	/** 購入レコード群 */
	private static BuyRecord[] buyRecord;

	/* 特売情報 */
	/** 指定数量 */
	private static int specQuantity;

	/** 対象行数 */
	private static int targetLines;

	/** 対象レコード群 */
	private static TargetRecord[] targetRecord;

	/** 特売レコード */
	private static SaleRecord saleRecord;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		q1();
	}

	private static void q1() {
		// 購入レコードの添字
		int k;
		int kp;
		// 対象レコードの添字
		int t;
		int w;

		/* 検索部 */
		k = startPtr;
		t = 1;

		while((k > 0) && (t <= targetLines)) {
			if(buyRecord[k].number == targetRecord[t].number) {
				targetRecord[t].number = buyRecord[k].number;
				/* a */
				/* b */
			} else {
				if(buyRecord[k].number < targetRecord[t].number) {
					/* a */
				} else {
					/* b */
				}
			}

		}

		/* 計算部 */
		w = 0;
		for(t = 1; t<= targetLines; t++) {
			w = w + targetRecord[t].quantity;
		}
		saleRecord.quantity = w / targetLines;

		/* 更新部 */
		kp = 0;
		k = startPtr;

		while((k > 0) && (buyRecord[k].number < saleRecord.number)) {
			kp = k;
			k = buyRecord[k].ptr;
		}

		if (saleRecord.quantity > 0) {
			buyLines++;
			if (kp > 0) {
				buyRecord[k].ptr = 0/* c */;
			} else {
				startPtr = 0/* c */;
			}

			buyRecord[buyLines].ptr = 0/* d */;
			buyRecord[buyLines].number = saleRecord.number;
			buyRecord[buyLines].name = saleRecord.name;
			buyRecord[buyLines].unitPrice = saleRecord.unitPrice;
			buyRecord[buyLines].quantity = saleRecord.quantity;
			buyRecord[buyLines].price = saleRecord.unitPrice * saleRecord.quantity;
		}
	}


}
