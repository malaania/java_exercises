package com.capgemini.taxi;

import java.util.ArrayList;
import java.util.List;

public class CityTaxiTransport {
	//TODO pole powinno być prywatne
	public List<Taxi> allTaxi; 
	
	public CityTaxiTransport() {
		allTaxi = new ArrayList<Taxi>();
	}
	public CityTaxiTransport(List<Taxi> existingTaxi) {
		allTaxi = existingTaxi;
	}	
	public CityTaxiTransport addTaxi(Taxi car){
		allTaxi.add(car);
		return this;
	}
	public CityTaxiTransport removeTaxi(int id){
		//TODO do usuwania użyj iteratora
		for(Taxi car: allTaxi){
			if (car.taxiID == id){
				allTaxi.remove(car);
				return this;
			}
		}
		return null;
	}
}
