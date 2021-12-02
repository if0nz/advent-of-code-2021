package it.ifonz.puzzles;

import java.io.IOException;
import java.util.List;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day03 extends AbstractDay {

	static List<String> lines;

	public static void main(String[] args) throws IOException {
		
		(new Day03()).run();
		
	}

	public void init() throws IOException {
		lines = FileReader.readLines("src/main/resources/day02.txt");
	}
	
	public void part1() {
	}

	public void part2() {
	}

}
