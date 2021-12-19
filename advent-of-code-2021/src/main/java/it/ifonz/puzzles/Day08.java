package it.ifonz.puzzles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day08 extends AbstractDay {

	public List<String> signals = new ArrayList<>();
	public List<String> values = new ArrayList<>();
	public List<String> lines = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		(new Day08()).run();

	}

	public void init() throws IOException {
		lines = FileReader.readLines("src/main/resources/day08.txt");
		for (var l : lines) {
			var tokens = l.split(" \\| ");
			signals.add(tokens[0]);
			values.add(tokens[1]);
		}
	}

	public void part1() {
		var s = values.stream().mapToLong(value -> Arrays.stream(value.split(" "))
			.filter(v -> 
			Arrays.asList(2, 3, 4, 7).contains(v.length())
			).count()).sum();
		System.out.println(s);
	}

	public void part2() {
		var x = lines.stream().mapToInt(l -> {
			var sv = l.split(" \\| ");
			var value = sv[1];
			var patterns = getPatterns(l);
			var sb = new StringBuilder();
			for (var n : value.split(" ")) {
				sb.append(IntStream.range(0, 10).filter(i -> isAnagramCounting(n, patterns.get(i))).findFirst().getAsInt());
			}
			return Integer.valueOf(sb.toString());
		}).sum();
		System.out.println(x);
	}

	private List<String> getPatterns(String value) {
		
		String one = "", four = "", seven = "", zero = "", eight = "", nine = "", six = "", two = "", three = "", five = "";
		List<String> filtered = Arrays.stream(value.split(" ")).filter(v -> Arrays.asList(2, 4, 3, 7).contains(v.length())).collect(Collectors.toList());
		for (var token : filtered) {
			switch (token.length()) {
			case 2:
				one = token;
				break;
			case 3:
				seven = token;
				break;
			case 4:
				four = token;
				break;
			case 7:
				eight = token;
			default:
				break;
			}
		}
		
		// identify zero, six, nine
		List<String> size6 = Arrays.stream(value.split(" ")).filter(v -> v.length() == 6).collect(Collectors.toList());
		for (var n : size6) {
			if (four.chars().filter(c -> n.contains(Character.toString(c))).count() == 4) // it's 9 
				nine= n;
			else if (one.chars().filter(c -> n.contains(Character.toString(c))).count() == 2) // it's 0
				zero = n;
			else // it's 6
				six = n;
		}
		
		// identify two, three, five
		List<String> size5 = Arrays.stream(value.split(" ")).filter(v -> v.length() == 5).collect(Collectors.toList());
		for (var n : size5) {
			if (nine.chars().filter(c -> n.contains(Character.toString(c))).count() == 5) {// it's 3 or 5
				if (one.chars().filter(c -> n.contains(Character.toString(c))).count() == 2) // it's 3
					three = n;
				else
					five = n; // it's 5
			} else // it's 2
				two = n;
			
		}
		return Arrays.asList(zero,one,two,three,four,five,six,seven,eight,nine);
	}

	// thx baeldung
	private boolean isAnagramCounting(String string1, String string2) {
		var CHARACTER_RANGE= 256;
	    if (string1.length() != string2.length()) {
	        return false;
	    }
	    int count[] = new int[CHARACTER_RANGE];
	    for (int i = 0; i < string1.length(); i++) {
	        count[string1.charAt(i)]++;
	        count[string2.charAt(i)]--;
	    }
	    for (int i = 0; i < CHARACTER_RANGE; i++) {
	        if (count[i] != 0) {
	            return false;
	        }
	    }
	    return true;
	}
}
