package it.ifonz.puzzles.optimized;

import java.io.IOException;

import it.ifonz.puzzles.Day01;

public class Opt01 extends Day01 implements Opt {

	public static void main(String[] args) throws IOException {
		(new Opt01()).combo();
	}
	
	public void combo() throws IOException {
		super.init();
		var c1 = 0;
		var c3 = 0;
		c1 +=  numbers.get(1) > numbers.get(0) ? 1 : 0;
		c1 +=  numbers.get(2) > numbers.get(1) ? 1 : 0;
		var sum1 = sum3(numbers,2);
		var sum2 = 0;
		for (var i = 3; i < numbers.size(); i++) {
			sum2 = sum1;
			Integer current = numbers.get(i);
			sum1 += current - numbers.get(i-3);
			c1 += current > numbers.get(i-1) ? 1 : 0;
			c3 += sum1 > sum2 ? 1 : 0;
		}
		
		System.out.println(c1);
		System.out.println(c3);
	}
}
