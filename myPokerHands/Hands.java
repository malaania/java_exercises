package com.capgemini.myPokerHands;

import java.util.HashMap;
import java.util.Map;

public enum Hands {
	HC_3(3), HC_4(4), HC_5(5), HC_6(6), HC_7(7), HC_8(8), HC_9(9), HC_10(10), HC_J(11), HC_Q(12), HC_K(13), HC_A(14),
	ONE_PAIR(15), TWO_PAIRS(16), THREE(17), STRAIGHT(18), FLUSH(19), FULL(20), FOUR(21), STRAIGHT_FLUSH(22), ROYAL_FLUSH(23);
	
	private int numVal;


    Hands(final int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
    
    private static Map<Integer, Hands> mapCardsValues = new HashMap<Integer, Hands>();

    static {
        for (Hands hand : Hands.values()) {
            mapCardsValues.put(hand.numVal, hand);
        }
    }


    public static Hands valueOf(int value) {
        return mapCardsValues.get(value);
    }

	
}
