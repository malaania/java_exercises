package com.capgemini.fibonacci;

/**
 * Created by ldrygala on 2015-01-23. 
 * F0 F1 F2 F3 F4 F5 F6 F7 F8 F9 F10 F11 F12 F13 F14 F15 F16  F17  F18  F19 
 * 1  1  2  3  5  8  13 21 34 55 89  144 233 377 610 987 1597 2584 4181
 */
public class Fibonacci {
	//TODO rozwiąż za pomocą rekurencji, będzie łatwiej i czytelniej. Brakuje testów.
	public static long fib1(int n) {
		long fib[] = {1,1};
		int iteracje = n/2;
		for(int i = 0; i < iteracje; i++){
			fib[0] = fib[0] + fib[1];			
			fib[1] = fib[0] + fib[1];
		}
		return (n%2 == 0) ? fib[0] : fib[1] ;
	}
}