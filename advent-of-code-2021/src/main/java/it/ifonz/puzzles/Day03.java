package it.ifonz.puzzles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day03 extends AbstractDay {

	protected static List<String> lines;

	public static void main(String[] args) throws IOException {
		
		(new Day03()).run();
		
	}

	public void init() throws IOException {
		lines = FileReader.readLines("src/main/resources/day03.txt");
	}
	
	public void part1() {
		var gamma = new StringBuilder();
		var epsilon = new StringBuilder();
		IntStream.range(0, lines.get(0).length()).forEach(i-> {
			var cnt = lines.stream().filter(l -> l.charAt(i) == '0').count();
			if (cnt >= lines.size()/2) {
				gamma.append("0");
				epsilon.append("1");
			} else {
				gamma.append("1");
				epsilon.append("0");
			}
		});
		var g_int = Integer.parseInt(gamma.toString(),2);
		var e_int = Integer.parseInt(epsilon.toString(),2);
		System.out.println(g_int*e_int);
	}

	public void part2() {
		var o2Lines = new ArrayList<>(lines);
		var co2Lines = new ArrayList<>(lines);
		IntStream.range(0, lines.get(0).length()).forEach(i-> {
			if (o2Lines.size() > 1) {
				var cnt = o2Lines.stream().filter(l -> l.charAt(i) == '0').count();
				var o2Tmp = o2Lines.stream().filter(l -> cnt > o2Lines.size()/2 ? l.charAt(i) == '0' : l.charAt(i) == '1').collect(Collectors.toList());
				o2Lines.clear();
				o2Lines.addAll(o2Tmp);
			}
			if (co2Lines.size() > 1) { 
				var cnt = co2Lines.stream().filter(l -> l.charAt(i) == '0').count();
				var co2Tmp = co2Lines.stream().filter(l -> cnt > co2Lines.size()/2 ? l.charAt(i) == '1' : l.charAt(i) == '0').collect(Collectors.toList());
				co2Lines.clear();
				co2Lines.addAll(co2Tmp);
			}
		});
		var o2_int = Integer.parseInt(o2Lines.get(0),2);
		var co2_int = Integer.parseInt(co2Lines.get(0),2);
		System.out.println(o2_int*co2_int);
	}

}
