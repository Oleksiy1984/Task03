package ua.orlovskyi.task03.part1;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

	private static String data = readFile("part1.txt");

	public static String convert1(String input) {
		StringBuilder sb = new StringBuilder(300);
		String[] firstSplit = input.split("\n");
		for (int i = 1; i < firstSplit.length; i++) {
			String[] secondSplit = firstSplit[i].split(";");
			sb.append(secondSplit[0] + " ==> " + secondSplit[2] + "\n");
		}
		return sb.toString();
	}

	public static String convert2(String input) {
		String[] firstSplit = input.split("\\r\\n");
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < firstSplit.length; i++) {
			String[] secondSplit = firstSplit[i].split("[\\s\\;]");
			sb.append(secondSplit[2] + " " + secondSplit[1] + " (email: " + secondSplit[3] + ")\n");

		}
		return sb.toString().trim();

	}

	public static String convert3(String input) {
		Pattern p = Pattern.compile("(.+;)(.+;)(.+)(@.+)");
		Matcher m = p.matcher(input);
		StringBuilder sb = new StringBuilder();

		while (m.find()) {
			String domen = m.group(4).substring(1);
			String login = m.group(1).substring(0, m.group(1).length() - 1);
			Pattern p2 = Pattern.compile(domen);
			Matcher m2 = p2.matcher(sb);
			if (m2.find()) {
				Pattern p3 = Pattern.compile(domen + ".+");
				Matcher m3 = p3.matcher(sb);
				m3.find();
				sb.insert(m3.end(), ", " + login);
			} else {
				sb.append("\n"+domen + " ==> "+ login);
			}
		}
		return sb.toString().trim();

	}

	public String convert4(String input) {
		Random rand = new Random();
		String[] text = input.split(System.lineSeparator());
		StringBuilder sb = new StringBuilder();
		sb.append(text[0] + ";Password" + System.lineSeparator());
		for (int i = 1; i < text.length; i++) {
			int random = rand.nextInt(9999);
			while (random < 1001) {
				random = rand.nextInt(9999);
			}
			sb.append(text[i] + ";" + random + System.lineSeparator());

		}
		return sb.toString();
	}

	public static String readFile(String fileName) {
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(fileName));
		} catch (IOException e) {
			System.err.println("Part 1 error. FileNotFound");
		}
		return new String(encoded, StandardCharsets.UTF_8);
		// StringBuilder sb = new StringBuilder(300);
		// try(Scanner scanner = new Scanner(new File(fileName));) {
		// while (scanner.hasNextLine()) {
		// sb.append(scanner.nextLine()).append("\n");
		// }
		// } catch (IOException ex) {
		// System.err.println("Part 1 error. FileNotFound");
		// }
		// return sb.toString().trim();
	}

	public static void main(String[] args) {
		// System.out.println(data);
		// System.out.println(convert1(data));
		// System.out.print(Part1.convert2(data));
		System.out.println(Part1.convert3(data));
		// System.out.print(Part1.convert4(input)+System.lineSeparator());
	}
}
