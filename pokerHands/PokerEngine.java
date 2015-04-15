package com.capgemini.pokerHands;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class PokerEngine {

	private static Map<List<Integer>, Hands> pattern2Hand = ImmutableMap
			.<List<Integer>, Hands> builder() //
			.put(asList(4, 1), Hands.FOUR) //
			.put(asList(3, 1, 1), Hands.THREE) //
			.put(asList(2, 2, 1), Hands.TWO_PAIRS) //
			.put(asList(2, 1, 1, 1), Hands.ONE_PAIR) //
			.put(asList(3, 2), Hands.FULL) //
			.build();

	public static Hands parseHande(String input) {

		List<Integer> values = new ArrayList<Integer>(countFigures(input)
				.values());
		Collections.sort(values, Collections.reverseOrder());

		return pattern2Hand.get(values);
	}

	private static Map<Character, Integer> countFigures(String input) {
		String[] cards = input.split(" ");
		Map<Character, Integer> figure2Occurrence = new HashMap<Character, Integer>();
		for (String card : cards) {
			char figure = card.charAt(0);
			Integer occurrence = figure2Occurrence.get(figure);
			if (occurrence == null) {
				figure2Occurrence.put(figure, 1);
			} else {
				figure2Occurrence.put(figure, occurrence + 1);
			}
		}
		return figure2Occurrence;
	}

}
