package it.ifonz.puzzles.optimized;

import java.io.IOException;
import java.time.Instant;

public class AllOpts {
	
	public static void main(String[] args) throws IOException {
		Opt[] days 	= new Opt[]{new Opt01(), new Opt02(), new Opt03(), new Opt04()};
		for (var d : days) {
			var begin = Instant.now().toEpochMilli();
			d.combo();
			System.out.println(d.getClass().getSimpleName()+" Exec time -> "+(Instant.now().toEpochMilli()-begin)+" ms");
		}
	}

}
