package it.ifonz.puzzles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import it.ifonz.common.AbstractDay;
import it.ifonz.common.FileReader;

public class Day04 extends AbstractDay {

	public class Card {
		public boolean completed = false;
		public List<List<Integer>> rows;
	}

	protected List<Integer> drawnNumbers = new ArrayList<>();
	protected ArrayList<Card> cards = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		(new Day04()).run();

	}

	public void init() throws IOException {
		var lines = FileReader.readLines("src/main/resources/day04.txt");
		List<List<Integer>> card = new ArrayList<List<Integer>>();
		for (var l : lines) {
			if (l.contains(","))
				for (var n : l.split(",")) {
					drawnNumbers.add(Integer.parseInt(n));
				}
			else if (StringUtils.isNoneBlank(l)) {
				var row = new ArrayList<Integer>();
				for (var n : l.split(" ")) {
					if (!n.equals(""))
						row.add(Integer.parseInt(n));
				}
				row.add(-5); // -5 = no numbers marked; 0 = row completed
				card.add(row);
			} else {
				card.add(Arrays.asList(-5, -5, -5, -5, -5, -5));
				Card c = new Card();
				c.completed = false;
				c.rows = card;
				cards.add(c);
				card = new ArrayList<>();
			}
		}
		cards.remove(0);
		Card c = new Card();
		c.completed = false;
		c.rows = card;
		card.add(Arrays.asList(-5, -5, -5, -5, -5, -5));
		cards.add(c);
	}

	public void part1() {
		var extracted = new ArrayList<Integer>();
		for (var n : drawnNumbers) {
			extracted.add(n);
			for (var card : cards) {
				for (var row : card.rows) {
					for (var i = 0; i < 5; i++) {
						if (row.get(i) == n) {
							var marked = row.get(5).intValue() + 1;
							var marked_c = card.rows.get(5).get(i).intValue() + 1;
							row.set(5, marked);
							card.rows.get(5).set(i, marked_c);
							if (marked == 0 || marked_c == 0) {
								var s = card.rows.stream().flatMap(List::stream)
										.filter(x -> !extracted.contains(x) && x > 0)
										.collect(Collectors.summarizingInt(x -> x)).getSum();
								System.out.println(s * n);
								return;
							}
						}
					}
				}
			}
		}
	}

	public void part2() {
		var extracted = new ArrayList<Integer>();
		var winningCards = 0;
		for (var card : cards) {
			card.completed = false;
			for (var row : card.rows) {
				row.set(5, -5);
			}
			card.rows.remove(5);
			card.rows.add(Arrays.asList(-5, -5, -5, -5, -5, -5));
		}
		for (var n : drawnNumbers) {
			extracted.add(n);
			for (var card : cards) {
				if (!card.completed) {
					for (var j = 0; j < 5; j++) {
						var row = card.rows.get(j);
						for (var i = 0; i < 5; i++) {
							if (row.get(i) == n) {
								var marked = row.get(5).intValue() + 1;
								var marked_c = card.rows.get(5).get(i).intValue() + 1;
								row.set(5, marked);
								card.rows.get(5).set(i, marked_c);
								if (marked == 0 || marked_c == 0) {
									card.completed = true;
									winningCards++;
									if (winningCards == cards.size()) {
										var s = card.rows.stream().flatMap(List::stream)
												.filter(x -> !extracted.contains(x) && x > 0)
												.collect(Collectors.summarizingInt(x -> x)).getSum();
										System.out.println(s * n);
										return;
									}
								}

							}

						}
					}
				}
			}
		}
	}

}
