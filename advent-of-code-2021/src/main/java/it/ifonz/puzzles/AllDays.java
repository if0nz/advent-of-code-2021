package it.ifonz.puzzles;

import java.io.IOException;
import java.time.Instant;

import it.ifonz.common.AbstractDay;

public class AllDays {

	public static void main(String[] args) throws IOException {
		AbstractDay[] days 	= new AbstractDay[]{new Day01(), new Day02()};
		for (var d : days) {
			var begin = Instant.now().toEpochMilli();
			d.run();
			System.out.println("Exec time -> "+(Instant.now().toEpochMilli()-begin)+" ms");
		}
	}
	
}
