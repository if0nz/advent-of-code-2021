package it.ifonz.puzzles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day11 extends AbstractDay {

	public List<String> lines = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		(new Day11()).run();

	}

	public void init() throws IOException {
		lines = FileReader.readLines("src/main/resources/day11.txt");
	}

	public void part1() {
		var temp = new ArrayList<>(lines); // ;-D
		int flashes = 0;
		for (int step = 0; step < 100; step++) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					var c = lines.get(i).charAt(j) + 1;
					lines.set(i, lines.get(i).substring(0, j) + Character.toString(c) + lines.get(i).substring(j + 1));
					if (c == '9' + 1) {
						increaseNeighbour(i - 1, j - 1);
						increaseNeighbour(i, j - 1);
						increaseNeighbour(i + 1, j - 1);
						increaseNeighbour(i + 1, j);
						increaseNeighbour(i + 1, j + 1);
						increaseNeighbour(i, j + 1);
						increaseNeighbour(i - 1, j + 1);
						increaseNeighbour(i - 1, j);
					}
				}
			}
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					var c = lines.get(i).charAt(j);
					if (c > '9') {
						flashes++;
						lines.set(i, lines.get(i).substring(0, j) + "0" + lines.get(i).substring(j + 1));
					}
				}
			}
		}
		System.out.println(flashes);
		lines = temp; // ;-D
	}

	private void increaseNeighbour(int i, int j) {
		try {
			var c = lines.get(i).charAt(j);
			if (c > '9')
				return;

			c++;
			lines.set(i, lines.get(i).substring(0, j) + Character.toString(c) + lines.get(i).substring(j + 1));
			if (c == '9' + 1) {
				increaseNeighbour(i - 1, j - 1);
				increaseNeighbour(i, j - 1);
				increaseNeighbour(i + 1, j - 1);
				increaseNeighbour(i + 1, j);
				increaseNeighbour(i + 1, j + 1);
				increaseNeighbour(i, j + 1);
				increaseNeighbour(i - 1, j + 1);
				increaseNeighbour(i - 1, j);
			}
			return;
		} catch (Exception e) {

		}
	}

	public void part2() {
		var flashes = 0;
		var step = 0;
		while(flashes != 100) {
			flashes=0;
			step++;
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					var c = lines.get(i).charAt(j) + 1;
					lines.set(i, lines.get(i).substring(0, j) + Character.toString(c) + lines.get(i).substring(j + 1));
					if (c == '9' + 1) {
						increaseNeighbour(i - 1, j - 1);
						increaseNeighbour(i, j - 1);
						increaseNeighbour(i + 1, j - 1);
						increaseNeighbour(i + 1, j);
						increaseNeighbour(i + 1, j + 1);
						increaseNeighbour(i, j + 1);
						increaseNeighbour(i - 1, j + 1);
						increaseNeighbour(i - 1, j);
					}
				}
			}
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					var c = lines.get(i).charAt(j);
					if (c > '9') {
						flashes++;
						lines.set(i, lines.get(i).substring(0, j) + "0" + lines.get(i).substring(j + 1));
					}
				}
			}
		}
		System.out.println(step);
	}
}
