package it.ifonz.puzzles.optimized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.ifonz.puzzles.Day04;

public class Opt04 extends Day04 implements Opt {

	public static void main(String args[]) throws IOException {
		(new Opt04()).combo();
	}

	public void combo() throws IOException {
		super.init();
		var extracted = new ArrayList<Integer>();
		var winningCards = 0;

		for (var n : drawnNumbers) {
			extracted.add(n);
			for (var card : cards) {
				if (!card.completed) {
					for (var j = 0; j < 5; j++) {
						var row = card.rows.get(j);
						for (var i = 0; i < 5; i++) {
							Integer curr_number = row.get(i);
							if (curr_number == n) {
								var marked = row.get(5) + 1;
								List<Integer> dummy_row = card.rows.get(5);
								var marked_c = dummy_row.get(i) + 1;
								row.set(5, marked);
								dummy_row.set(i, marked_c);
								if (marked == 0 || marked_c == 0) {
									card.completed = true;
									winningCards++;
									if (winningCards == 1 || winningCards == cards.size()) {
										var sum = 0;
										for (var x = 0; x < 5; x++) {
											for (var y = 0; y < 5; y++) {
												Integer num = card.rows.get(x).get(y);
												sum += extracted.contains(num) ? 0 : num; 
											}
										}
										System.out.println(sum*n);
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
