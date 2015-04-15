package com.capgemini.pythagorean;

//import java.util.List;

/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * a^2 + b^2 = c^2
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
public class Pythagorean {
	public static long solution(){
		for(long b = 1; b < 500; b++){
			for (long a = 1; a < b; a++){
				long c = 1000 - a - b;
				long c2 = a*a + b*b;
				if(c*c== c2){					
					return a*b*c;
				}
			}
		}
		return 0;
	}
}
