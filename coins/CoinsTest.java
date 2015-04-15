package com.capgemini.coins;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CoinsTest {

	@Test
	public void test1() {
		List<Integer> monety = new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1,
				1, 1));
		int score = Coins.solution(monety);
		assertThat(score).isEqualTo(4);
	}

	public void test2() {
		List<Integer> monety = new ArrayList<Integer>(Arrays.asList(1, 1, 0, 1,
				0, 0));
		int score = Coins.solution(monety);
		assertThat(score).isEqualTo(4);
	}

	public void test3() {
		List<Integer> monety = new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1,
				1, 0));
		int score = Coins.solution(monety);
		assertThat(score).isEqualTo(5);
	}

	public void test4() {
		List<Integer> monety = new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1,
				0, 0));
		int score = Coins.solution(monety);
		assertThat(score).isEqualTo(4);
	}

}
