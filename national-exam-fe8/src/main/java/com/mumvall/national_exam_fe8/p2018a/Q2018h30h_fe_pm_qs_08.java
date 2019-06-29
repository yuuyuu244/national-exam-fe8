package com.mumvall.national_exam_fe8.p2018a;

/**
 * 基本情報技術者試験(2018年秋 問8)をJavaで表してみたクラス
 * @author mumvall
 */
public class Q2018h30h_fe_pm_qs_08 {

    /**
     * プログラムを実行するうえで一番最初に実行されるメソッド。
     * ※勝手に追加
     * @param args
     */
    public static void main(String[] args) {
        /** 整数式の文字配列 */
        char expression[] = {'2','×','(','3','4','－', '(', '5', '＋', '6', '7', ')', '÷', '8', ')'};

        /** computeメソッドを実行したときの答え */
        int answer = compute(expression, expression.length);

        // 答えの表示
        System.out.println("答え:\t" + answer);
    }

    /**
     * 整数式を受け取ってその値を返すプログラム。
     * @param expression 整数式の配列
     * @param expLen 整数式の配列の長さ
     */
    public static int compute(char[] expression, int expLen) {
        /** 演算子 */
        char[] operator = new char[100];

        /** 演算子の数 */
        int opCnt;

        /** 優先度(大きい：優先度高, 小さい：優先度低) */
        int[] priority = new int[100];

        /** 値の配列 */
        int[] value = new int[100];

        /** 文字を一時的に入れておくところ */
        char chr;

        /** 繰り返しのカウンター */
        int i;

        /** 一番高い優先度の添え字 */
        int ip;

        /** ネスト(入れ子)の判定数値 */
        int nest;

        // *****************************************
        // *************** 解析処理 *****************
        // *****************************************
        // 初期化：演算子の数
        opCnt = 0;

        // 初期化：値配列の一番最初に0をいれておく
        value[0] = 0;

        // 初期化：ネスト(入れ子)の判定数値
        nest = 0;

        // 整数式の文字をすべて実行する
        for(i = 0; i < expLen; i++) {
            // 対象の文字を取りだす。
            chr = expression[i];

            // もし数字だったら...
            if (('0' <= chr) && (chr <= '9')) {
                value[opCnt] = 10 * value[opCnt] + Character.getNumericValue(chr);
            }

            // もし演算子(＋－×÷)
            if ((chr == '＋') || (chr == '－') || (chr == '×') || (chr == '÷')) {

                // 演算子の配列に追加
                operator[opCnt] = chr;

                // もし"＋"か"－"だったら...
                if((chr == '＋') || (chr == '－')) {
                    priority[opCnt] = nest + 1;

                // もし"×"か"÷"だったら...
                } else {
                    priority[opCnt] = nest + 2;
                }

                // 演算子の数をカウント
                opCnt = opCnt + 1;

                // 初期化
                value[opCnt] = 0;
            }

            // もし"カッコ入り"だったら...
            if(chr == '(') {
                nest = nest + 10;
            }

            // もし"カッコ閉"だったら...
            if(chr == ')') {
                nest = nest - 10;
            }
        }

        // *****************************************
        // *************** 計算処理 *****************
        // *****************************************
        while(opCnt > 0) {
            // 配列の表示 ※菊谷が勝手に追加
            print(value, operator, priority, opCnt);

            // 初期化：一番高い優先度の添え字
            ip = 0;

            // 優先度が一番高い(priorityが一番大きい)演算子を探す。
            for(i = 1; i< opCnt; i++) {

                // 優先度が高いほうの添え字をipに格納する。
                // ※最大値を求めるアルゴリズムの同じ考え方
                if(priority[ip] < priority[i]) {
                    ip = i;
                }
            }

            // 優先度が高い演算子(一番最初に計算する)
            chr = operator[ip];

            // もし足し算だったら...
            if (chr == '＋') {
                value[ip] = value[ip] + value[ip + 1];
            }

            // もし引き算だったら...
            if (chr == '－') {
                value[ip] = value[ip] - value[ip + 1];
            }

            // もし掛け算だったら...
            if (chr == '×') {
                value[ip] = value[ip] * value[ip + 1];
            }

            // もし割り算だったら...
            if (chr == '÷') {
                value[ip] = value[ip] / value[ip + 1];
            }

            // 配列の後ろを詰める処理
            for(i = ip + 1; i < opCnt; i++) {
                // 計算した後ろの値配列を詰める
                value[i] = value[i + 1];
                // 計算した後ろの演算子配列を詰める
                operator[i - 1] = operator[i];
                // 計算した後ろの優先度配列を詰める
                priority[i - 1] = priority[i];
            }

            // 演算子の数を減らす。
            opCnt = opCnt - 1;
        }

        // 整数式の値を返す。
        return value[0];
    }

    // ---------------- ここから下は関係ないので無視ってね!!  -----------------------

    /**
     * 表示するメソッド。
     * ※菊谷が勝手に作ったメソッド。
     * @param value 値配列
     * @param operator 演算子配列
     * @param priority 優先度配列
     * @param opCnt 演算子配列の長さ
     */
    public static void print(int[] value, char[] operator, int[] priority, int opCnt) {
        System.out.println("--------------------------------------------------");
        printIntArg(value, opCnt + 1, "値 value\t");
        printCharArg(operator, opCnt, "演算子 oprator");
        printIntArg(priority, opCnt, "優先度 priority");
        System.out.println("--------------------------------------------------");
    }

    /**
     * char型配列を表示する。
     * ※勝手に作ったメソッド。
     * @param a char型配列
     * @param length 配列の長さ
     * @param argsName 配列名
     */
    public static void printCharArg(char[] a, int length, String argsName) {
        System.out.print(argsName + "\t[");
        for(int cnt = 0; cnt < length; cnt++) {
            if (cnt < length -1) {
                System.out.print(a[cnt] + ",\t");
            } else {
                System.out.print(a[cnt]);
            }


        }
        System.out.println("]");
    }

    /**
     * int型配列を表示する。
     * ※勝手に作ったメソッド。
     * @param a int型配列
     * @param length 配列の長さ
     * @param argsName 配列名
     */
    public static void printIntArg(int[] a, int length, String argsName) {
        boolean isLast = false;
        System.out.print(argsName + "\t[");
        for(int cnt = 0; cnt < length; cnt++) {
            if (cnt < length -1) {
                System.out.print(a[cnt] + ",\t");
            } else {
                System.out.print(a[cnt]);
            }

        }
         System.out.println("]");
    }

}
