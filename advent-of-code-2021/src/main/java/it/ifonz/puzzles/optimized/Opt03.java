package it.ifonz.puzzles.optimized;

import java.io.IOException;
import java.util.ArrayList;

import it.ifonz.puzzles.Day03;

public class Opt03 extends Day03 implements Opt {

	public static void main(String args[]) throws IOException {
		(new Opt03()).combo();
	}

	public void combo() throws IOException {
		super.init();
		var gamma = 0;
		var epsilon = 0;
		var o2Lines = new ArrayList<>(lines);
		var co2Lines = new ArrayList<>(lines);
		for (var i = 0; i< lines.get(0).length(); i++) {
			var cnt = 0;
			for (var line : lines) {
				cnt += line.charAt(i) - '0';
			}
			gamma<<=1;
			epsilon<<=1;
			if (cnt >= lines.size()/2) {
				epsilon^=1;
			} else {
				gamma^=1;
			}
			if (o2Lines.size() > 1) {
				var o2cnt = 0;
				for (var o2line : o2Lines) {
					o2cnt += o2line.charAt(i) -'0';
				}
				var o2Tmp = new ArrayList<String>(); 
				for (var o2line : o2Lines) {
					if (o2cnt >= o2Lines.size()/2.0 && o2line.charAt(i) == '1' || o2cnt < o2Lines.size()/2.0 && o2line.charAt(i) == '0') {
						o2Tmp.add(o2line);
					} 
				}
				o2Lines = o2Tmp;
			}
			if (co2Lines.size() > 1) { 
				var co2cnt = 0;
				for (var co2line : co2Lines) {
					co2cnt += co2line.charAt(i) -'0';
				}
				var co2Tmp = new ArrayList<String>();
				for (var co2line : co2Lines) {
					if (co2cnt >= co2Lines.size()/2.0 && co2line.charAt(i) == '0' || co2cnt < co2Lines.size()/2.0 && co2line.charAt(i) == '1') {
						co2Tmp.add(co2line);
					} 
				}
				co2Lines = co2Tmp;
			}
		}
		System.out.println(gamma*epsilon);
		var o2_int = Integer.parseInt(o2Lines.get(0),2);
		var co2_int = Integer.parseInt(co2Lines.get(0),2);
		System.out.println(o2_int*co2_int);
	}
}
