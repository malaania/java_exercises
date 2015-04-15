package com.capgemini.myPokerHands;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PokerHandsTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void shouldParseOnePair() {
		String input = "5H 5C 6S 7S KD";
		Hands hand = PokerEngine.parseHande(input);
		assertThat(hand).isEqualTo(Hands.ONE_PAIR);
	}

	@Test
	public void shouldParseTwoPairs() {
		String input = "5H 5C 6S 6H KD";
		Hands hand = PokerEngine.parseHande(input);
		assertThat(hand).isEqualTo(Hands.TWO_PAIRS);
	}

	@Test
	public void shouldParseThree() {
		String input = "5H 5C 5S 6H KD";
		Hands hand = PokerEngine.parseHande(input);
		assertThat(hand).isEqualTo(Hands.THREE);
	}

	@Test
	public void shouldParseFour() {
		String input = "5H 5C 5S 5D KD";
		Hands hand = PokerEngine.parseHande(input);
		assertThat(hand).isEqualTo(Hands.FOUR);
	}

	@Test
	public void shouldParseFull() {
		String input = "5H 5C 5S KH KD";
		Hands hand = PokerEngine.parseHande(input);
		assertThat(hand).isEqualTo(Hands.FULL);
	}
	@Test
	public void shouldParseRoyalFlush() {
		String input = "AH JH QH KH TH";
		Hands hand = PokerEngine.parseHande(input);
		assertThat(hand).isEqualTo(Hands.ROYAL_FLUSH);
	}	
	@Test
	public void shouldParseFlush() {
		String input = "AH JH 9H KH TH";
		Hands hand = PokerEngine.parseHande(input);
		assertThat(hand).isEqualTo(Hands.FLUSH);
	}
	@Test
	public void shouldParseStraightFlush() {
		String input = "4H 7H 8H 5H 6H";
		Hands hand = PokerEngine.parseHande(input);
		assertThat(hand).isEqualTo(Hands.STRAIGHT_FLUSH);
	}
	@Test
	public void shouldParseStraight() {
		String input = "4H 7C 8H 5H 6H";
		Hands hand = PokerEngine.parseHande(input);
		assertThat(hand).isEqualTo(Hands.STRAIGHT);
	}
	@Test
	public void shouldHighestCard() {
		String input = "4H 2C 8H TH 6H";
		Hands hand = PokerEngine.parseHande(input);
		assertThat(hand).isEqualTo(Hands.HC_10);
	}
	@Test
	public void shouldCheckWinner() {
		String input1 = "4H 7H 8H 5H 6H";
		String input2 = "5H 5C 5S KH KD";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(input1);
	}
	@Test
	public void shouldCheckWinner2() {
		String input1 = "5H 5C 5S 6H KD";
		String input2 = "4H 2C 8H 5H 6H";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(input1);
	}
	@Test
	public void shouldCheckWinnerHighestCard() {
		String input1 = "2H 5C 7S 6H KD";
		String input2 = "4H 2C 8H 5H 6H";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(input1);
	}
	@Test
	public void shouldCheckWinnerDrawRoyalFlush() {
		String input1 = "AD JD TD KD QD";
		String input2 = "KS AS QS JS TS";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(null);
	}
	@Test
	public void shouldCheckWinnerStraightFlush() {
		String input1 = "7D 6D 8D 9D TD";
		String input2 = "JS TS QS 9S 8S";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(input2);
	}
	@Test
	public void shouldCheckWinnerStraightFlushDraw() {
		String input1 = "9D JD TD KD QD";
		String input2 = "KH 9H QH JH TH";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(null);
	}
	@Test
	public void shouldCheckWinnerDrawFours() {
		String input1 = "9D 9H 9C 9S QD";
		String input2 = "KS KC KD KH TH";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(input2);
	}
	@Test
	public void shouldCheckWinnerDrawFull() {
		String input1 = "9D 9H 9C QS QD";
		String input2 = "KS KC KD TH TH";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(input2);
	}
	@Test
	public void shouldCheckWinnerDrawThree() {
		String input1 = "9D 9H 9C KS QD";
		String input2 = "TS TC TD 5H 7H";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(input2);
	}
	@Test
	public void shouldCheckWinnerDrawStraight() {
		String input1 = "TD JH 9C KS QD";
		String input2 = "TS 9C 8D JH 7H";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(input1);
	}
	@Test
	public void shouldCheckWinnerDrawFlush() {
		String input1 = "TD 3D 9D KD QD";
		String input2 = "TC 9C 2C 7C QC";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(input1);
	}
	@Test
	public void shouldCheckWinnerDrawTwoPairs() {
		String input1 = "TD TH QS KD QD";
		String input2 = "KH 9H KC 9C QH";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(input2);
	}
	@Test
	public void shouldCheckWinnerDrawTwoEqualPairs() {
		String input1 = "JH JD 7S 7H QD";
		String input2 = "7D JS JC 7C 3C";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(input1);
	}
	@Test
	public void shouldCheckWinnerDrawOnePairNull() {
		String input1 = "QH QD TC 7S 2H";
		String input2 = "QD QC TS 7H 2D";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(null);
	}
	@Test
	public void shouldCheckWinnerDrawOnePair() {
		String input1 = "QH QD TC 7S 2H";
		String input2 = "QD QC TS 7H 3D";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(input2);
	}
	@Test
	public void shouldCheckWinnerDrawHighestCard() {
		String input1 = "7C JD 2H QH 3S";
		String input2 = "2H 4D QC TS JH";
		String winner = PokerEngine.checkWinner(input1, input2);
		assertThat(winner).isEqualTo(input2);
	}
	@Test
	public void shouldFromFile() throws IOException {
		assertTrue( PokerEngine.checkWinnerFromFile() == 376);
	}


}
