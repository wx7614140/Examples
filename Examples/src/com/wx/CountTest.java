package com.wx;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String words="python/pandas数据挖掘（十四)		-groupby,聚合，分组级运算.12章，00x";
		countChinese(words);
		countEnglish(words);
		countNumber(words);
		countBlank(words);
	}
	public static void countChinese(String words) {
//		Pattern p= Pattern.compile("[\u0391-\uFFE5]");//包含全角符号
		Pattern p= Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m=p.matcher(words);
		int count=0;
		while(m.find()) {
			count++;
//			System.out.println(m.group());
		}
		System.out.println(count);
	}
	public static void countEnglish(String words) {
		Pattern p= Pattern.compile("[a-zA-Z]");
		Matcher m=p.matcher(words);
		int count=0;
		while(m.find()) {
			count++;
//			System.out.println(m.group());
		}
		System.out.println(count);
	}
	public static void countNumber(String words) {
		Pattern p= Pattern.compile("[0-9]");
		Matcher m=p.matcher(words);
		int count=0;
		while(m.find()) {
			count++;
//			System.out.println(m.group());
		}
		System.out.println(count);
	}
	public static void countBlank(String words) {
		Pattern p= Pattern.compile("[\\\\s]");
		Matcher m=p.matcher(words);
		int count=0;
		while(m.find()) {
			count++;
//			System.out.println(m.group());
		}
		System.out.println(count);
	}
}
