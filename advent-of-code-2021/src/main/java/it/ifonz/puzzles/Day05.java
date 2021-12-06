package it.ifonz.puzzles;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day05 extends AbstractDay {

	public List<Line> lines;
	
	public class Line {
		int x1,x2,y1,y2;
	}
	
	public static void main(String[] args) throws IOException {

		(new Day05()).run();

	}

	public void init() throws IOException {
		var l = FileReader.readLines("src/main/resources/day05.txt");
		lines = l.stream().map(li -> {
			var coords = li.split(" -> ");
			var line = new Line();
			line.x1 = Integer.parseInt(coords[0].split(",")[0]);
			line.y1 = Integer.parseInt(coords[0].split(",")[1]);
			line.x2 = Integer.parseInt(coords[1].split(",")[0]);
			line.y2 = Integer.parseInt(coords[1].split(",")[1]);
			return line;
		}).collect(Collectors.toList());
	}

	public void part1() {
		// horizontal (x1 == x2) and vertical lines (y1 == y2)
		var h_v = lines.stream().filter(l -> l.x1 == l.x2 || l.y1 == l.y2).collect(Collectors.toList());
		var max_x = h_v.stream().mapToInt(l -> Integer.max(l.x1, l.x2)).max().getAsInt();
		var max_y = h_v.stream().mapToInt(l -> Integer.max(l.y1, l.y2)).max().getAsInt();
		var grid = new int[max_x+1][max_y+1];
		for (var line : h_v) {
			if (line.x1 == line.x2) {
				for (var i = Integer.min(line.y1,line.y2); i <= Integer.max(line.y1, line.y2); i++) {
					grid[line.x1][i]++;
				}
			} else {
				for (var i = Integer.min(line.x1,line.x2); i <= Integer.max(line.x1, line.x2); i++) {
					grid[i][line.y1]++;
				}	
			}
		}
		System.out.println(Arrays.stream(grid).mapToLong(x -> Arrays.stream(x).filter(n -> n>=2).count()).sum());
	}

	public void part2() {
		var max_x = lines.stream().mapToInt(l -> Integer.max(l.x1, l.x2)).max().getAsInt();
		var max_y = lines.stream().mapToInt(l -> Integer.max(l.y1, l.y2)).max().getAsInt();
		var grid = new int[max_x+1][max_y+1];
		for (var line : lines) {
			if (line.x1 == line.x2) {
				for (var i = Integer.min(line.y1,line.y2); i <= Integer.max(line.y1, line.y2); i++) {
					grid[line.x1][i]++;
				}
			} else if (line.y1 == line.y2) {
				for (var i = Integer.min(line.x1,line.x2); i <= Integer.max(line.x1, line.x2); i++) {
					grid[i][line.y1]++;
				}	
			} else {
				// diagonal
				if (line.x1 <= line.x2) {
					if (line.y1 < line.y2) {
						for (var i = 0 ; i <= line.x2 - line.x1; i++) {
							grid[line.x1+i][line.y1+i]++;
						}
					} else {
						for (var i = 0; i <= line.x2 - line.x1; i++) {
							grid[line.x1+i][line.y1-i]++;
						}
					}
				} else {
					if (line.y1 < line.y2) {
						for (var i = 0 ; i <= line.x1 - line.x2; i++) {
							grid[line.x1-i][line.y1+i]++;
						}
					} else {
						for (var i = 0; i <= line.x1 - line.x2; i++) {
							grid[line.x1-i][line.y1-i]++;
						}
					}
				}
			}
		}
		System.out.println(Arrays.stream(grid).mapToLong(x -> Arrays.stream(x).filter(n -> n>=2).count()).sum());
	}

}
