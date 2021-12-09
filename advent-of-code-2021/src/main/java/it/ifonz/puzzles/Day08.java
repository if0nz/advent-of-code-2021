package it.ifonz.puzzles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day08 extends AbstractDay {

	public List<String> signals = new ArrayList<>();
	public List<String> values = new ArrayList<>();
	public List<String> lines = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		(new Day08()).run();

	}

	public void init() throws IOException {
		lines = FileReader.readLines("src/main/resources/day08.txt");
		for (var l : lines) {
			var tokens = l.split(" \\| ");
			signals.add(tokens[0]);
			values.add(tokens[1]);
		}
	}

	public void part1() {
		var s = values.stream().mapToLong(value -> Arrays.stream(value.split(" "))
			.filter(v -> 
			Arrays.asList(2, 3, 4, 7).contains(v.length())
			).count()).sum();
		System.out.println(s);
	}

	public void part2() {
		values.forEach(s -> System.out.println(getPatterns(s)));
	}

	private ArrayList<String> getPatterns(String value) {
		var pattern = new ArrayList<String>();
		for (var i = 0; i < 7; i++) {
			pattern.add("abcdefg");
		}
		Arrays.stream(value.split(" ")).filter(v -> Arrays.asList(2, 4, 3, 7).contains(v.length())).forEach(token -> {
			switch (token.length()) {
			case 2:
				token.chars().forEach(c -> {
					pattern.get(0).replace(Character.toString(c), "");
					pattern.get(1).replace(Character.toString(c), "");
					pattern.get(3).replace(Character.toString(c), "");
					pattern.get(4).replace(Character.toString(c), "");
					pattern.get(6).replace(Character.toString(c), "");
				});
				break;
			case 3:
				token.chars().forEach(c -> {
					pattern.get(1).replace(Character.toString(c), "");
					pattern.get(3).replace(Character.toString(c), "");
					pattern.get(4).replace(Character.toString(c), "");
					pattern.get(5).replace(Character.toString(c), "");
				});
				break;
			case 4:
				token.chars().forEach(c -> {
					pattern.get(0).replace(Character.toString(c), "");
					pattern.get(4).replace(Character.toString(c), "");
					pattern.get(6).replace(Character.toString(c), "");
				});
				break;
			default:
				break;
			}
		});
		return pattern;
	}

}
