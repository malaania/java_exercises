package com.capgemini.taxi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
	public int userID;
	private Location userCoordinates;
	private List<Taxi> nearestTaxis; 
	
	public User(int n, double d, double e){
		userID = n;
		userCoordinates = new Location(d,e);
		nearestTaxis = new ArrayList<Taxi>();
	}
	
	public void calcDistToClient(Taxi taxi1){
		double x = userCoordinates.x - taxi1.taxiCoordinates.x;
		double y = userCoordinates.y - taxi1.taxiCoordinates.y;
		taxi1.distanceToClient = Math.sqrt(x*x + y*y);
	}
	
	public void findNearest(CityTaxiTransport allCars){
		if(allCars.allTaxi.isEmpty() == false){
			for(Taxi taxi: allCars.allTaxi){
				calcDistToClient(taxi);
				 if(taxi.distanceToClient <= 1 && taxi.availabilty == true ){
					 nearestTaxis.add(taxi);
				 }
			}
			Collections.sort(nearestTaxis, new CompareDistance());
		}
	}
	
	public Taxi takeTaxi(CityTaxiTransport allCars){
		findNearest(allCars);
		//TODO == true jest nie potrzebne, jeśli jest puste to nie trzeba przyrównywać do true 
		if(nearestTaxis.isEmpty() == true){
			//TODO nie rzucamy NullPointerException, znajdź lepszy wyjątek
			throw new NullPointerException("No taxi available");
		}
		nearestTaxis.get(0).availabilty = false;
		return nearestTaxis.get(0);
	}
	
	public void leaveTaxi(Taxi taxi){
		taxi.availabilty = true;
	}
}
