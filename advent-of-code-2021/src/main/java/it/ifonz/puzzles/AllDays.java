package it.ifonz.puzzles;

import java.io.IOException;
import java.time.Instant;

import it.ifonz.common.AbstractDay;

public class AllDays {

	public static void main(String[] args) throws IOException {
		AbstractDay[] days 	= new AbstractDay[]{new Day01(), new Day02(), new Day03(), new Day04(), new Day05(), new Day06(), new Day07()};
		for (var d : days) {
			var begin = Instant.now().toEpochMilli();
			d.run();
			System.out.println(d.getClass().getSimpleName()+" Exec time -> "+(Instant.now().toEpochMilli()-begin)+" ms");
		}
	}
	
}
