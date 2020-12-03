package com.guigu.test;

/**
 * Hello world!
 *
 */
public class AppCount {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		String text = "同时,wirecard还宣布将以最多1.09亿欧元(约合人民币8.4亿元)收购中国支付公司商银信80%股权,剩余20%将在两年后完成收购。";

		String content = "两年后";
		int length = content.length();

		int indexOf = text.indexOf(content);
		System.out.println(indexOf + ",\r\n" + (length + indexOf));
	}
}
