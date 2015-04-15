package com.capgemini.pythagorean;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PythagoreanTest {

	@Test
	public void test() {
		long product = Pythagorean.solution();
		assertThat(product).isEqualTo(31875000);
	}

}
