package it.ifonz.puzzles;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day10 extends AbstractDay {

	public List<String> lines = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		(new Day10()).run();

	}

	public void init() throws IOException {
		lines = FileReader.readLines("src/main/resources/day10.txt");
	}

	public void part1() {
		var open = "([{<";
		var s = lines.stream().mapToInt(l -> {
			var q = new ArrayDeque<Character>();
			for (var c : l.toCharArray()) {
				if (open.contains(Character.toString(c))) { // add opening char in the deque
					q.add(c);
				} else { // validate closing char
					var o = q.removeLast(); // actual closing char
					if (Math.abs(c - o) > 2) {
						return switch (c) {
						case ')' -> 3;
						case ']' -> 57;
						case '}' -> 1197;
						case '>' -> 25137;
						default -> 0;
						};
					}
				}
			}
			return 0;
		}).sum();
		System.out.println(s);
	}

	public void part2() {
		var open = "([{<";
		var incompletes = lines.stream().map(l -> {
			var q = new ArrayDeque<Character>();
			for (var c : l.toCharArray()) {
				if (open.contains(Character.toString(c))) { // add opening char in the deque
					q.add(c);
				} else { // validate closing char
					var o = q.removeLast(); // actual closing char
					if (Math.abs(c - o) > 2) {
						return "";
					}
				}
			}
			if (!q.isEmpty()) {
				var sb = new StringBuilder();
				for (var c : q)
					sb.append(c);
				return sb.reverse().toString();
			}
			return "";
		}).filter(st -> !st.isBlank()).collect(Collectors.toList());
		var scores = Arrays.asList('\0','(','[','{','<');
		var scored = incompletes.stream().map(s -> {
			var score = 0l;
			for (var c : s.toCharArray()) {
				score*=5;
				score+=scores.indexOf(c);
			}
			return score;
		}).collect(Collectors.toList());
		scored.sort(Long::compareTo);
		System.out.println(scored.get(scored.size()/2));
	}
}
