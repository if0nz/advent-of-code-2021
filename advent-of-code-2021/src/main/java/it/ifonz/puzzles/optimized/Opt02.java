package it.ifonz.puzzles.optimized;

import java.io.IOException;

import it.ifonz.puzzles.Day02;

public class Opt02 extends Day02 implements Opt {

	public static void main(String args[]) throws IOException {
		(new Opt02()).combo();
	}

	public void combo() throws IOException {
		super.init();
		var coords = new int[] { 0, 0 };
		var coords2 = new int[] {0,0,0};
		for (var line : lines) {
			var tokens = line.split(" ");
			Integer qty = Integer.valueOf(tokens[1]);
			switch (tokens[0]) {
			case "forward":   coords[0]+=qty;  coords2[0] += qty; coords2[1] += coords2[2] * qty;break;
			case "up": coords[1]-=qty;coords2[2] -= qty; break;
			case "down": coords[1]+=qty; coords2[2] += qty; break;
			default: break;
			};
		}
		System.out.println(coords[0]*coords[1]);
		System.out.println(coords2[0]*coords2[1]);
	}
}
