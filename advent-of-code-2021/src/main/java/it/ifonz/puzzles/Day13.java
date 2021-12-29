package it.ifonz.puzzles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day13 extends AbstractDay {

	public List<String> lines = new ArrayList<>();
	public boolean[][] grid;
	public List<String> foldAlong = new ArrayList<>();
	public int maxX;
	public int maxY;

	public static void main(String[] args) throws IOException {

		(new Day13()).run();

	}

	public void init() throws IOException {
		lines = FileReader.readLines("src/main/resources/day13.txt");
		var dots = lines.stream().filter(l -> l.contains(",")).collect(Collectors.toList());
		maxX = dots.stream().mapToInt(l -> Integer.valueOf(l.split(",")[0])).max().getAsInt();
		maxY = dots.stream().mapToInt(l -> Integer.valueOf(l.split(",")[1])).max().getAsInt();
		grid = new boolean[maxX + 1][maxY + 1];
		for (var d : dots) {
			var t = d.split(",");
			grid[Integer.valueOf(t[0])][Integer.valueOf(t[1])] = true;
		}
		for (var i = dots.size() + 1; i < lines.size(); i++) {
			foldAlong.add(lines.get(i).substring(11));
		}
	}

	public void part1() {
		var lastX = maxX;
		var lastY = maxY;
		var fold = foldAlong.get(0);
		var t = fold.split("=");
		if ("y".equals(t[0])) {
			var y = Integer.valueOf(t[1]);
			lastY = y;
			for (var i = 0; i < grid.length; i++) {
				for (var j = 1; j < grid[i].length - y; j++) {
					grid[i][y - j] |= grid[i][y + j];
				}
			}
		} else {
			var x = Integer.valueOf(t[1]);
			lastX = x;
			for (var i = 1; i < grid.length - x; i++) {
				for (var j = 0; j < grid[i].length; j++) {
					grid[x - i][j] |= grid[x + i][j];
				}
			}
		}
		var cnt = 0;
		for (var i = 0; i < lastX + 1; i++) {
			for (var j = 0; j < lastY + 1; j++) {
				cnt += grid[i][j] ? 1 : 0;
			}
		}
		System.out.println(cnt);
	}

	public void part2() {
		for (var fold : foldAlong) {
			var t = fold.split("=");
			if ("y".equals(t[0])) {
				var y = Integer.valueOf(t[1]);
				for (var i = 0; i < maxX; i++) {
					for (var j = 1; j < maxY - y; j++) {
						grid[i][y - j] |= grid[i][y + j];
					}
				}
				maxY = y;
			} else {
				var x = Integer.valueOf(t[1]);
				for (var i = 1; i < maxX - x; i++) {
					for (var j = 0; j < grid[i].length; j++) {
						grid[x - i][j] |= grid[x + i][j];
					}
				}
				maxX = x;
			}
		}
		for (var j = 0; j < maxY + 1; j++) {
			for (var i = 0; i < maxX + 1; i++) {
				System.out.print(grid[i][j] ? "#" : ".");
			}
			System.out.println("");
		}
	}

}
