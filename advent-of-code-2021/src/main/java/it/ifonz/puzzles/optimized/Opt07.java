package it.ifonz.puzzles.optimized;

import java.io.IOException;

import it.ifonz.puzzles.Day07;

public class Opt07 extends Day07 implements Opt {

	public static void main(String args[]) throws IOException {
		(new Opt07()).combo();
	}

	public void combo() throws IOException {
		super.init();
		numbers.sort(Integer::compare);
		var median = numbers.get(numbers.size() / 2);
		var s = numbers.stream().mapToLong(x -> Math.abs(x - median)).sum();
		System.out.println(s);

		var avg = Math.round(numbers.stream().mapToInt(x -> x).average().getAsDouble());
		var s2 = numbers.stream().mapToLong(x -> {
			var n = Math.abs(x - avg);
			return n * (n + 1) / 2;
		}).sum();

		System.out.println(s2);
	}
}
