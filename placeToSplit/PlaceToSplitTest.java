package com.capgemini.placeToSplit;

import static org.junit.Assert.*;
import org.junit.Test;

public class PlaceToSplitTest {

	@Test
	public void test1() {
		int[] nums = {1, 1, 1, 2, 1};
		boolean result = PlaceToSplit.canBalance(nums);
		assertTrue(result);
	}
	@Test
	public void test2() {
		int[] nums = {1, 1, 1, 7};
		boolean result = PlaceToSplit.canBalance(nums);
		assertFalse(result);
	}
	@Test
	public void test3() {
		int[] nums = {10,10};
		boolean result = PlaceToSplit.canBalance(nums);
		assertTrue(result);
	}
	@Test
	public void test4() {
		int[] nums = {1};
		boolean result = PlaceToSplit.canBalance(nums);
		assertFalse(result);
	}
	@Test
	public void test5() {
		int[] nums = {1089,17, 89, 1016,1};
		boolean result = PlaceToSplit.canBalance(nums);
		assertTrue(result);
	}
	@Test
	public void test6() {
		int[] nums = {45, 432, 64};
		boolean result = PlaceToSplit.canBalance(nums);
		assertFalse(result);
	}

}
