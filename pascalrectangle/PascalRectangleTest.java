package com.capgemini.pascalrectangle;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;


public class PascalRectangleTest {

	@Test
	public void checkRow7Column2() {
		long expected_value = 21; 
		long returned_value = PascalRectangle.pascal(2,7);
		assertThat(returned_value).isEqualTo(expected_value);
	}
	@Test
	public void checkRow5Column1() {
		long expected_value = 5; 
		long returned_value = PascalRectangle.pascal(1,5);
		assertThat(returned_value).isEqualTo(expected_value);
	}
	@Test
	public void checkRow9Column3() {
		long expected_value = 84; 
		long returned_value = PascalRectangle.pascal(3,9);
		assertThat(returned_value).isEqualTo(expected_value);
	}
	@Test
	public void checkRow9Column7() {
		long expected_value = 36; 
		long returned_value = PascalRectangle.pascal(7,9);
		assertThat(returned_value).isEqualTo(expected_value);
	}
	@Test
	public void checkRow6Column1() {
		long expected_value = 6; 
		long returned_value = PascalRectangle.pascal(1,6);
		assertThat(returned_value).isEqualTo(expected_value);
	}
	@Test
	public void checkRow8Column6() {
		long expected_value = 28; 
		long returned_value = PascalRectangle.pascal(6,8);
		assertThat(returned_value).isEqualTo(expected_value);
	}
	@Test
	public void checkRow0Column0() {
		long expected_value = 1; 
		long returned_value = PascalRectangle.pascal(0,0);
		assertThat(returned_value).isEqualTo(expected_value);
	}
	@Test
	public void checkRow6Column2() {
		long expected_value = 15; 
		long returned_value = PascalRectangle.pascal(2,6);
		assertThat(returned_value).isEqualTo(expected_value);
	}
	@Test
	public void checkInvalid() {
		long expected_value = 0; 
		long returned_value = PascalRectangle.pascal(12,11);
		assertThat(returned_value).isEqualTo(expected_value);
	}


}
