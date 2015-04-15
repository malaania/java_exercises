package com.capgemini.myPokerHands;

public enum CardValues {
	T(10), J(11), Q(12), K(13), A(14);

    private int numVal;

    CardValues(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }


}
