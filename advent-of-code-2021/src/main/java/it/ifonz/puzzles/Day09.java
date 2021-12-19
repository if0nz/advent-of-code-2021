package it.ifonz.puzzles;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day09 extends AbstractDay {

	public List<String> flows = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		(new Day09()).run();

	}

	public void init() throws IOException {
		flows = FileReader.readLines("src/main/resources/day09.txt");
	}

	public void part1() {
		var len = flows.get(0).length();
		var lowpoints = new StringBuilder();

		for (var i = 0; i < flows.size(); i++) { // vertical index
			for (var j = 0; j < len; j++) { // horizontal index
				var current = flows.get(i).charAt(j);
				if (check(current, i, j + 1) && check(current, i, j - 1) && check(current, i - 1, j)
						&& check(current, i + 1, j)) {
					lowpoints.append(current);
				}
			}
		}
		System.out.println(lowpoints.chars().mapToLong(Character::getNumericValue).sum() + lowpoints.length());
	}

	// given a digit, checked whether it's less than a neighbor value
	private boolean check(int digit, int x, int y) {
		try {
			return digit < flows.get(x).charAt(y);
		} catch (Exception e) {
			return true;
		}
	}
	
	// given a location, checkes wheter it's 9 or it's out of the box and, if so, it's not been already visited
	private boolean check(int x, int y, HashSet<Coord> visited) {
		try {
			return flows.get(x).charAt(y) != '9' && !visited.contains(new Coord(x,y));
		} catch (Exception e) {
			return false;
		}
	}

	public void part2() {
		var basins = new ArrayList<Integer>();
		var len = flows.get(0).length();
//		for (var i = 0; i < flows.size(); i++) { // vertical index
//			for (var j = 0; j < len; j++) { // horizontal index
//				var current = flows.get(i).charAt(j);
//				if (check(current, i, j + 1) && check(current, i, j - 1) && check(current, i - 1, j)
//						&& check(current, i + 1, j)) {
//					int basinSize = 1;
//					var z = 0;
//					// find left
//					while (j - (++z) > 0 && flows.get(i).charAt(j-z) < '9') {
//						basinSize++;
//					}
//					z = 0;
//					// find right
//					while (j + (++z) < len && flows.get(i).charAt(j+z) < '9') {
//						basinSize++;
//					}
//					// find up
//					z = 0;
//					while (i-(++z) > 0 && flows.get(i-z).charAt(j) < '9') {
//						basinSize++;
//					}
//					// find down
//					while (i+(++z) < flows.size() && flows.get(i+z).charAt(j) < '9') {
//						basinSize++;
//					}
//					basins.add(basinSize);
//				}
//			}
//		}
		
		for (var i = 0; i < flows.size(); i++) { // vertical index
			for (var j = 0; j < len; j++) { // horizontal index
				var current = flows.get(i).charAt(j);
				if (check(current, i, j + 1) && check(current, i, j - 1) && check(current, i - 1, j)
						&& check(current, i + 1, j)) {
					basins.add(visitTheBasin(i, j));
				}
			}
		}
		var m1 = basins.stream().max(Integer::compareTo).get();
		basins.remove(m1);
		var m2 = basins.stream().max(Integer::compareTo).get();
		basins.remove(m2);
		var m3 = basins.stream().max(Integer::compareTo).get();
		System.out.println(m1*m2*m3);
	}
	
	private int visitTheBasin(int i, int j) {
		var queue = new ArrayDeque<Coord>();
		var visited = new HashSet<Coord>();
		queue.add(new Coord(i, j));
		while (!queue.isEmpty()) {
			var c = queue.poll();
			visited.add(c);
			if(check(c.x, c.y + 1, visited)) 
				queue.add(new Coord(c.x,c.y+1));
			if(check(c.x, c.y - 1, visited)) {
				queue.add(new Coord(c.x, c.y - 1));
			}
			if(check(c.x - 1, c.y, visited)) {
				queue.add(new Coord(c.x - 1, c.y));
			}
			if(check(c.x + 1, c.y, visited)) {
				queue.add(new Coord(c.x + 1, c.y));
			}	
		}
		return visited.size();
	}

	record Coord(int x, int y) {
	}
}
