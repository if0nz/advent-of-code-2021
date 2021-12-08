package it.ifonz.puzzles;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day07 extends AbstractDay {

	public List<Integer> numbers;
	
	public static void main(String[] args) throws IOException {

		(new Day07()).run();

	}

	public void init() throws IOException {
		var line = FileReader.readLine("src/main/resources/day07.txt");
		numbers = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
	}

	public void part1() {
		var min = numbers.stream().mapToInt(x -> x).min().getAsInt();
		var max = numbers.stream().mapToInt(x -> x).max().getAsInt();
		List<Long> distances = IntStream.rangeClosed(min, max).mapToLong(i -> numbers.stream().mapToLong(crab -> Math.abs(crab-i)).sum()).boxed().collect(Collectors.toList());
		System.out.println((distances.stream().mapToLong(x -> x).min().getAsLong()));
	}

	
	public void part2() {
		var min = numbers.stream().mapToInt(x -> x).min().getAsInt();
		var max = numbers.stream().mapToInt(x -> x).max().getAsInt();
		List<Long> distances = IntStream.rangeClosed(min, max).mapToLong(i -> numbers.stream().mapToLong(crab -> {
			int n = Math.abs(crab-i);
			return n*(n+1)/2;}
		).sum()).boxed().collect(Collectors.toList());
		var y = distances.stream().mapToLong(x -> x).min().getAsLong();
		System.out.println(distances.stream().mapToLong(x -> x).min().getAsLong());
		System.out.println(distances.indexOf(y));
	}
	
	

}
