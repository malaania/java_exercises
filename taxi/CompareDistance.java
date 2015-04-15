package com.capgemini.taxi;

import java.util.Comparator;

public class CompareDistance implements Comparator<Taxi> {
	public int compare(Taxi t1, Taxi t2){
		return t1.distanceToClient.compareTo(t2.distanceToClient);
	}
}