package it.ifonz.puzzles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day06 extends AbstractDay {

	List<Integer> numbers;
	
	public static void main(String[] args) throws IOException {

		(new Day06()).run();

	}

	public void init() throws IOException {
		var line = FileReader.readLine("src/main/resources/day06.txt");
		numbers = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
	}

	public void part1() {
		var fishes = new ArrayList<>(numbers);
		for (var i = 0; i < 80; i++) {
			List<Integer> newFishes = new ArrayList<>();
			for (var j = 0; j < fishes.size(); j++) {
				var fish = fishes.get(j)-1;
				if (fish < 0) {
					newFishes.add(8);
					fish = 6;
				}
				fishes.set(j, fish);
			}
			fishes.addAll(newFishes);
		}
		System.out.println(fishes.size());
	}

	public void part2() {
		var fishes = new ArrayList<>(numbers);
		for (var i = 0; i < 80; i++) {
			System.out.println(fishes);
			List<Integer> newFishes = new ArrayList<>();
			for (var j = 0; j < fishes.size(); j++) {
				var fish = fishes.get(j)-1;
				if (fish < 0) {
					newFishes.add(8);
					fish = 6;
				}
				fishes.set(j, fish);
			}
			fishes.addAll(newFishes);
		}
		System.out.println(fishes.size());
	}

}
