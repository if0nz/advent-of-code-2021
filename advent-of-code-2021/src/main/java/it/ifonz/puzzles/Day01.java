package it.ifonz.puzzles;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day01 extends AbstractDay {

	static List<Integer> numbers;
	
	public static void main(String[] args) throws IOException {
		
		(new Day01()).run();
		
	}
	
	public void init() throws IOException {
		numbers = FileReader.readIntegerLines("src/main/resources/day01.txt");	
	}
	
	public void part1()  {
		var c = IntStream.range(1, numbers.size()).filter(i -> numbers.get(i) > numbers.get(i-1)).count();
		System.out.println(c);
	}
	
	public void part2()  {
		var c = IntStream.range(3, numbers.size()).filter(i -> sum3(numbers, i) > sum3(numbers, i-1)).count();
		System.out.println(c);
	}
	
	private static int sum3(List<Integer> numbers, int i) {
		return numbers.get(i)+numbers.get(i-1)+numbers.get(i-2);
	}
}
