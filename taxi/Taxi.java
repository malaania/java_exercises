package com.capgemini.taxi;

public class Taxi {
	//TODO wszystkie pola powinny być prywatne
	public int taxiID;
	public Location taxiCoordinates;
	public boolean availabilty;
	public Double distanceToClient;
	
	public Taxi(int n, double d, double e){
		taxiID = n;
		taxiCoordinates = new Location(d,e);
		availabilty = true;		
	}
	
	public Taxi(int n){
		taxiID = n;
		taxiCoordinates = new Location(0,0);
		availabilty = true;		
	}
	
	public int compareTo(Taxi t)
    {
        return distanceToClient.compareTo(t.distanceToClient);
    }

}
