package ua.orlovskyi.task03.part5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {
	private static final String[] NUMBERS = new String[] { "I", "II", "III",
			"IV", "V", "VI", "VII", "VIII", "IX", "X" };

	public static String decimal2Roman(int x) {
		StringBuilder number = new StringBuilder();
		int j = 0;
		if (x >= 10 && x % 10 == 0) {
			j = 1;
		}
		for (int i = 0; i < x / 10 + 1 - j; i++) {
			if ((i != x / 10 || x % 10 == 0) && x >= 10) {
				number.append(NUMBERS[9]);

			} else {
				number.append(NUMBERS[x % 10 - 1]);
			}

		}
		int loc  = 0;
		if (x >= 100) {
			Pattern p = Pattern.compile("X{10,}?");
			Matcher m = p.matcher(number);
			while (m.find()) {
				number.replace(m.start(), m.end(), "C");
				m = p.matcher(number);
				loc = x %100;
			}
		}
if (loc == 0){
		if (x >= 90 && x < 100) {
			Pattern p = Pattern.compile("(.+)(X{8})");
			Matcher m = p.matcher(number);
			m.find();
			number.replace(m.start(2), m.end(2), "C");
		}
		if (x >= 40 && x < 50) {
			Pattern p = Pattern.compile("(.+)(X{3})");
			Matcher m = p.matcher(number);
			m.find();
			number.replace(m.start(2), m.end(2), "L");
		}
		if (x >= 50 && x < 90) {
			Pattern p = Pattern.compile("(X{5})");
			Matcher m = p.matcher(number);
			m.find();
			number.replace(m.start(1), m.end(1), "L");
		}
}else{
	if (loc >= 90 && loc < 100) {
		Pattern p = Pattern.compile("(.+)(X{8})");
		Matcher m = p.matcher(number);
		m.find();
		number.replace(m.start(2), m.end(2), "C");
	}
	if (loc >= 40 && loc < 50) {
		Pattern p = Pattern.compile("(.+)(X{3})");
		Matcher m = p.matcher(number);
		m.find();
		number.replace(m.start(2), m.end(2), "L");
	}
	if (loc >= 50 && loc < 90) {
		Pattern p = Pattern.compile("(X{5})");
		Matcher m = p.matcher(number);
		m.find();
		number.replace(m.start(1), m.end(1), "L");
	}
}
		return number.toString();
	}

	public static int roman2Decimal(String str) {
		int value = 0;
		for (int i = 0; i < str.length(); i++) {
			char s = str.charAt(i);
			switch (s) {
			case 'I':
				if (i + 1 < str.length()
						&& (str.charAt(i + 1) == 'V' || str.charAt(i + 1) == 'X')) {
					value -= 1;
					break;
				}
				value += 1;
				break;
			case 'V':
				value += 5;
				break;
			case 'X':
				value += 10;
				break;
			case 'L':
				if (i > 0 && str.charAt(i - 1) == 'X') {
					value += 30;
					break;
				}
				value += 50;
				break;
			default:
				if (i > 0 && str.charAt(i - 1) == 'X') {
					value += 80;
					break;
				}
				value += 100;
			}
		}
		return value;
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 200; i++) {
			System.out.println(i + " ==> " + decimal2Roman(i) + " ==> "
					+ roman2Decimal(decimal2Roman(i)));
		}

	}

}
