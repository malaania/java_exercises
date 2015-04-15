package com.capgemini.myPokerHands;

import static java.util.Arrays.asList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

	
	public static int checkWinnerFromFile() throws IOException{
		File pokerFile = new File("C:/Users/anberezo/workspace/javaExercises/src/main/java/com/capgemini/pokerHands/poker.txt");
		FileReader readPoker = new FileReader(pokerFile);
		BufferedReader bufferedReader = new BufferedReader(readPoker);
		String line;
		List<String> pokerRounds = new ArrayList<String>();
		while ((line = bufferedReader.readLine()) != null) {
			pokerRounds.add(line);
		}
		readPoker.close();
		int countSuccess1 = 0;
		int countSuccess2 = 0;
		for(String round: pokerRounds){
			String player1 = round.substring(0,14);
			String player2 = round.substring(15,29);
			String winner = checkWinner(player1, player2);
			if(winner == player1){
				countSuccess1 +=1;
			} else if (winner == player2){
				countSuccess2 +=1;
			}
		}
		System.out.println("In " + pokerRounds.size() + " games:");
		System.out.println("Player 1 won " + countSuccess1 + " times");
		System.out.println("Player 2 won " + countSuccess2 + " times");
		return countSuccess1;
	}
	
	public static String checkWinner(String input1, String input2) {
		Hands hand1 = PokerEngine.parseHande(input1); 
		Hands hand2 = PokerEngine.parseHande(input2);
		
		int result1 =  hand1.getNumVal();
		int result2 =  hand2.getNumVal();
		if(result1 > result2){
			return input1;
		} else if(result1 < result2){
			return input2;
		}
		String result = checkDraw(input1, input2, hand1);
		if(result == input1){
			return input1;
		} else if(result == input2){
			return input2;
		}
		return null;
	}
	
	private static String checkDraw(String input1, String input2, Hands result) {
       switch(result) {
       		case HC_3: case HC_4: case HC_5: case HC_6: 
       		case HC_7: case HC_8: case HC_9: case HC_10:
       		case HC_J: case HC_Q: case HC_K: case HC_A:
       			return checkDrawHighestCard(input1, input2);
            case ONE_PAIR: 
            	return checkDrawOnePair(input1, input2);
            case TWO_PAIRS:
            	return checkDrawTwoPairs(input1, input2);            	
            case THREE:  
            	int h1 = figureToInt( getKeyFromValue(countFigures(input1),3));
            	int h2 = figureToInt( getKeyFromValue(countFigures(input2),3));
            	return (h1 > h2) ? input1 : input2; 
            case STRAIGHT:  
            	return checkStraight(input1, input2);  
            case FLUSH:
            	return checkDrawHighestCard(input1, input2);                	
            case FULL:  
            	int hand01 = figureToInt( getKeyFromValue(countFigures(input1),3));
            	int hand02 = figureToInt( getKeyFromValue(countFigures(input2),3));
            	return (hand01 > hand02) ? input1 : input2; 
            case FOUR:  
            	int hand1 = figureToInt( getKeyFromValue(countFigures(input1),4));
            	int hand2 = figureToInt( getKeyFromValue(countFigures(input2),4));
            	return (hand1 > hand2) ? input1 : input2;                     	
            case STRAIGHT_FLUSH:          	
        		return checkStraight(input1, input2);
            default: 
            	return null;
        }
	}	
	
	private static String checkDrawHighestCard(String input1, String input2) {
    	List<Integer> oneElem1 = countFigureValues(input1);
    	List<Integer> oneElem2 = countFigureValues(input2);
    	Collections.sort(oneElem1, Collections.reverseOrder());
    	Collections.sort(oneElem2, Collections.reverseOrder());
    	for(int i = 0; i < oneElem1.size();i++){
			if(oneElem1.get(i) != oneElem2.get(i)){
				return (oneElem1.get(i) < oneElem2.get(i)) ? input2 : input1;
			}
		}
		return null;
	}	
	
	private static String checkDrawOnePair(String input1, String input2) {
		Map<Character, Integer> figures1 = countFigures(input1);
    	Map<Character, Integer> figures2 = countFigures(input2);
    	Character pair1 = returnIfMatchesOccurence(figures1, 2);
    	Character pair2 = returnIfMatchesOccurence(figures2, 2); 
    	if(pair1 != pair2){
    		return (figureToInt(pair1) < figureToInt(pair2)) ? input2 : input1;
    	}
    	String restCards1 = input1.replaceAll(pair1 + ". ", "");
    	String restCards2 = input2.replaceAll(pair2 + ". ", ""); 
    	String result = checkDrawHighestCard(restCards1, restCards2);
    	if (result != null){
    		return (result == restCards1) ? input1 : input2;
    	}
    	return null;
	}	
	
	private static Character returnIfMatchesOccurence(Map<Character, Integer> figures1, int i) {
		for(Map.Entry<Character, Integer> fig1 : figures1.entrySet()){
    		if(fig1.getValue() == i){
    			return fig1.getKey();
    		}            		
    	}
		return null;
	}

	private static String checkDrawTwoPairs(String input1, String input2) {
		Map<Character, Integer> figures1 = countFigures(input1);
    	Map<Character, Integer> figures2 = countFigures(input2);
    	Character one1 = returnIfMatchesOccurence(figures1, 1);
    	Character one2 = returnIfMatchesOccurence(figures2, 1);
    	String pairs1 = input1.replaceAll(one1 + ". ", "");
    	String pairs2 = input2.replaceAll(one2 + ". ", ""); 
    	String result = checkDrawHighestCard(pairs1, pairs2);
    	if (result != null){
    		return (result == pairs1) ? input1 : input2;
    	} else if (one1 != one2){
    		return (figureToInt(one1) < figureToInt(one2)) ? input2 : input1 ;
    	} 
    	return null;     	
	}	

	private static String checkStraight(String input1, String input2) {
		List<Integer> intCardFigures1 = new ArrayList<Integer>(countFigureValues(input1));
    	List<Integer> intCardFigures2 = new ArrayList<Integer>(countFigureValues(input2));
		Collections.sort(intCardFigures1, Collections.reverseOrder());
		Collections.sort(intCardFigures2, Collections.reverseOrder());
		if(intCardFigures1.get(0) != intCardFigures2.get(0)){
			return (intCardFigures1.get(0)>intCardFigures2.get(0)) ? input1 : input2;
		}
		return null;		
	}

	private static int figureToInt(Character hand) {
		if(Arrays.asList('J','Q','K','A','T').contains(hand) ){				
			return CardValues.valueOf(String.valueOf(hand)).getNumVal();
		} else {				
			return Integer.parseInt(String.valueOf(hand));
		}		
	}

	public static Hands parseHande(String input) {	
		Map<Character, Integer> numFigures = countFigures(input);
		Map<Character, Integer> numColors = countColours(input);
		List<Integer> colors = new ArrayList<Integer>(numColors.values());
		List<Integer> flushValues = new ArrayList<Integer>(countFigureValues(input));
		Collections.sort(flushValues, Collections.reverseOrder());
		boolean consecutive = checkIfConsecutive(flushValues);
		if(colors.size()==1){
			if(consecutive==false){					
				return Hands.FLUSH;
			}
			if(flushValues.get(0)==14){
				return Hands.ROYAL_FLUSH;
			}
			return Hands.STRAIGHT_FLUSH;
		}
		if(consecutive==true){
			return Hands.STRAIGHT;
		}
		List<Integer> values = new ArrayList<Integer>(numFigures.values());
		Collections.sort(values, Collections.reverseOrder());
		Hands result = pattern2Hand.get(values);
		return (  result != null) ? result : Hands.valueOf(flushValues.get(0));
	}

	
	
	private static boolean checkIfConsecutive(List<Integer> flushValues) {
		for(int i = 1; i < flushValues.size();i++){
			if( (flushValues.get(i-1) - flushValues.get(i)) != 1){					
				return false;
			}
		}
		return true;
	}

	private static List<Integer> countFigureValues(String input) {
		String[] cards = input.split(" ");
		List<Integer> figures2Int = new ArrayList<Integer>();
		for(String card: cards){
			char figure = card.charAt(0);
			figures2Int.add(figureToInt(figure));
		}
		return figures2Int;
	}

	private static Map<Character, Integer> countColours(String input) {
		String[] cards = input.split(" ");
		Map<Character, Integer> color2Occurrence = new HashMap<Character, Integer>();
		for(String card: cards){
			char color = card.charAt(card.length()-1);
			Integer cOccurrence = color2Occurrence.get(color);
			if(cOccurrence == null){
				color2Occurrence.put(color, 1);
			} else {
				color2Occurrence.put(color, cOccurrence + 1);
			}
		}
		return color2Occurrence;
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
	
	public static Character getKeyFromValue(Map<Character, Integer> map, Integer value) {
	    for (Character charToFind : map.keySet()) {
	      if (map.get(charToFind).equals(value)) {
	        return charToFind;
	      }
	    }
	    return null;
	  }
}