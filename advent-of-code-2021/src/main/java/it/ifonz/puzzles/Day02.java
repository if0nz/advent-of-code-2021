package it.ifonz.puzzles;

import java.io.IOException;
import java.util.List;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day02 extends AbstractDay {

	static List<String> lines;

	public static void main(String[] args) throws IOException {
		
		(new Day02()).run();
		
	}

	public void init() throws IOException {
		lines = FileReader.readLines("src/main/resources/day02.txt");
	}
	
	public void part1() {
		int[] res = lines.stream().reduce(new int[] { 0, 0 }, // x,y
				(coords, line) -> {
					var tokens = line.split(" ");
					var c = switch (tokens[0]) {
					case "forward" -> new int[] { Integer.valueOf(tokens[1]), 0 };
					case "up" -> new int[] { 0, -Integer.valueOf(tokens[1]) };
					case "down" -> new int[] { 0, Integer.valueOf(tokens[1]) };
					default -> new int[] { 0, 0 };
					};
					return new int[] { coords[0] + c[0], coords[1] + c[1] };
				}, (a, b) -> a);
		System.out.println(res[0] * res[1]);
	}

	public void part2() {
		int[] res = lines.stream().reduce(new int[] { 0, 0, 0 }, // x,y,aim
				(coords, line) -> {
					var tokens = line.split(" ");
					return switch (tokens[0]) {
					case "forward" -> new int[] { coords[0] + Integer.valueOf(tokens[1]),
							coords[1] + coords[2] * Integer.valueOf(tokens[1]), coords[2] };
					case "up" -> new int[] { coords[0], coords[1], coords[2] - Integer.valueOf(tokens[1]) };
					case "down" -> new int[] { coords[0], coords[1], coords[2] + Integer.valueOf(tokens[1]) };
					default -> new int[] { 0, 0, 0 };
					};
				}, (a, b) -> a);
		System.out.println(res[0] * res[1]);
	}

}
