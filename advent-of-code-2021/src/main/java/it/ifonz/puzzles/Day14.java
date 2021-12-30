package it.ifonz.puzzles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day14 extends AbstractDay {

	public List<String> lines = new ArrayList<>();
	public String first;
	public Map<String, String> rules;
	public static void main(String[] args) throws IOException {

		(new Day14()).run();

	}

	public void init() throws IOException {
		lines = FileReader.readLines("src/main/resources/day14.txt");
		first = lines.get(0);
		rules = lines.subList(2, lines.size()).stream().map(s -> s.split(" -> ")).collect(Collectors.toMap(t -> t[0], t -> t[1]));
	}

	public void part1() {
		StringBuilder sb;
		var s = first;
		for (var i = 0; i < 10; i++) {
			sb = new StringBuilder();
			for (var j = 0; j < s.length()-1; j++) {
				var t = s.substring(j,j+2);
				sb.append(t.charAt(0)+rules.get(t));
			}
			sb.append(s.charAt(s.length()-1));
			s = sb.toString();
		}
		var charCount = s.codePoints().mapToObj(x->x)
		        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		var max = charCount.values().stream().max(Long::compareTo).get();
		var min = charCount.values().stream().min(Long::compareTo).get();
		System.out.println(max-min);
	}

	public void part2() {
		// counts the chars in string, init with input
		var charCount = first.chars().mapToObj(Character::toString)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		// counts the pairs in the string
		var pairCount = new HashMap<String, Long>();
		
		// init the map with the rule set, assigning 0 at every apir
		for (var k : rules.keySet()) pairCount.put(k, 0l);
		
		// init the map count with pairs from input
		for (var j = 0; j < first.length()-1; j++) {
			var t = first.substring(j,j+2);
			pairCount.put(t, pairCount.get(t)+1);
		}
		
		for (var i = 0; i < 40; i++) {
			
			// counting the pairs produced in this loop into a temp map
			var temp = new HashMap<String, Long>(); // f'n side effect
			for (var k : pairCount.keySet()) {
				temp.put(k, 0l);
			}
			
			// for every pair in the current "string"
			for (var p : pairCount.entrySet()) {
				if (p.getValue() > 0) {
					// given a pair AB and a rule AB -> C, applying rule removes AB to the string and adds AC and BC
					var t = p.getKey();
					var val = p.getValue(); // #occurrences of this pair in current string
					p.setValue(0l); // remove every occurrent of AB to current string
					String rule = rules.get(t);
					var p1 = t.charAt(0)+rule; // new pair AC
					var p2 = rule+t.charAt(1); // new pair BC
					temp.put(p1, temp.get(p1)+val); // add AC count to temp map
					temp.put(p2, temp.get(p2)+val); // add BC count to temp map
					Long long1 = charCount.get(rule); // #occurrences of C in current string
					charCount.put(rule, long1 == null ? val : long1+val); // add #occurrences of C to current string
				}
			}
			
			// for every pair in temp map
			for (var e : temp.entrySet()) {
				// update actual pairs map with new created pairs
				pairCount.put(e.getKey(), pairCount.get(e.getKey())+e.getValue());
			}
		}
		
		var max = charCount.values().stream().max(Long::compareTo).get();
		var min = charCount.values().stream().min(Long::compareTo).get();
		System.out.println(max-min);
	}

}
