package it.ifonz.puzzles;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day12 extends AbstractDay {

	public List<String> lines = new ArrayList<>();
	public Map<String, List<String>> graph = new HashMap<>();

	public static void main(String[] args) throws IOException {

		(new Day12()).run();

	}

	public void init() throws IOException {
		lines = FileReader.readLines("src/main/resources/day12.txt");
		for (var l : lines) {
			var t = l.split("-");
			var n1 = graph.get(t[0]);
			if (n1 == null) {
				n1 = new ArrayList<String>();
				graph.put(t[0], n1);
			}
			if (!"start".equals(t[1]))
				n1.add(t[1]);	
			var n2 = graph.get(t[1]);
			if (n2 == null) {
				n2 = new ArrayList<String>();
				graph.put(t[1], n2);
			}
			if (!"start".equals(t[0]))
				n2.add(t[0]);
		}
	}

	public void part1() {
		var strings = new ArrayDeque<String>();
		strings.add("start");
		var valid = new HashSet<String>();
		while (!strings.isEmpty()) {
			var s = strings.poll();
			var token = getLastToken(s);
			List<String> list = graph.get(token);
			if (list != null) {
				for (var n : list) {
					if ("end".equals(n)) {
						valid.add(s + "end");
					} else {
						if (StringUtils.isAllUpperCase(n) || !s.contains(n)) {
							strings.add(s + n);
						}
					}
				}
			}
		}
		System.out.println(valid.size());
	}

	public void part2() {
		var lower = graph.keySet().stream().filter(StringUtils::isAllLowerCase).collect(Collectors.toList());
		var strings = new ArrayDeque<String>();
		strings.add("start");
		var valid = new HashSet<String>();
		while (!strings.isEmpty()) {
			var s = strings.poll();
			var token = getLastToken(s);
			List<String> list = graph.get(token);
			if (list != null) {
				for (var n : list) {
					if ("end".equals(n)) {
						valid.add(s + "end");
					} else {
						if (StringUtils.isAllUpperCase(n) || isValid(s+n, lower)) {
							strings.add(s + n);
						}
					}
				}
			}
		}
		
		System.out.println(valid.size());
	}

	private String getLastToken(String s) {
		if ("start".equals(s)) return "start";
		var t = "";
		for (var i = s.length()-1; i > -1; i--) {
			t = s.charAt(i)+t;
			if (graph.containsKey(t)) break;
		}
		return t;
	}
	
	private boolean isValid(String s, List<String> lower) {
		if ("start".equals(s)) return false;
		var s1 = s.substring(5);
		var frequencies = lower.stream().map(l -> StringUtils.countMatches(s1, l)).collect(Collectors.toList());
		return frequencies.stream().filter(n -> n == 2).count() < 2 && frequencies.stream().filter(n -> n > 2).count() == 0;
	}
}
