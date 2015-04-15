package com.capgemini.taxi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class testTaxi {

	User user0 = new User(0, 0.25, 0.25);
	User user1 = new User(1, -0.75, 0.75);
	Taxi taxi0 = new Taxi(0, -0.75, 0.0);
	Taxi taxi1 = new Taxi(1, 1.25, 0.75);
	Taxi taxi2 = new Taxi(2, 0.5, 1.5);
	Taxi taxi3 = new Taxi(3, 1.0, 1.0);
	Taxi taxi4 = new Taxi(4, -0.25, -0.25);
	Taxi taxi5 = new Taxi(5, 0.0, 0.5);
	Taxi taxi6 = new Taxi(6, -0.25, 0.5);
	CityTaxiTransport taxiInTheCity = new CityTaxiTransport();
	
	@Test
	public void testCalcDistToClient() {
		user1.calcDistToClient(taxi0);
		assertThat(taxi0.distanceToClient).isEqualTo(0.75);
	}
	@Test
	public void testTakeNearest() {
		taxiInTheCity.addTaxi(taxi0).addTaxi(taxi1).addTaxi(taxi2).addTaxi(taxi3).addTaxi(taxi4).addTaxi(taxi5);
		assertThat(user0.takeTaxi(taxiInTheCity)).isEqualTo(taxi5);
	}
	@Test
	public void testCheckAvailability() {
		taxiInTheCity.addTaxi(taxi1).addTaxi(taxi2).addTaxi(taxi3).addTaxi(taxi4).addTaxi(taxi6);
		user0.takeTaxi(taxiInTheCity);
		assertThat(taxi6.availabilty).isEqualTo(false);
	}
	@Test
	public void testCheckAvailabiliy2() {
		taxiInTheCity.addTaxi(taxi0).addTaxi(taxi1).addTaxi(taxi2).addTaxi(taxi3).addTaxi(taxi4).addTaxi(taxi6);
		user0.takeTaxi(taxiInTheCity);
		assertThat(user1.takeTaxi(taxiInTheCity)).isEqualTo(taxi0);
	}
	@Test
	public void testCheckLeaveTaxi() {
		taxiInTheCity.addTaxi(taxi0).addTaxi(taxi1).addTaxi(taxi2).addTaxi(taxi3).addTaxi(taxi4).addTaxi(taxi6);
		user0.takeTaxi(taxiInTheCity);
		user0.leaveTaxi(taxi6);
		assertThat(user1.takeTaxi(taxiInTheCity)).isEqualTo(taxi6);
	}
	@Test
	public void testNoTaxiException1() {
		try{
			taxiInTheCity.addTaxi(taxi6);
			user0.takeTaxi(taxiInTheCity);
			user1.takeTaxi(taxiInTheCity);
		}
		catch (NullPointerException e){
			System.out.println(e.getMessage());
		}
	}
	@Test(expected = NullPointerException.class)
	public void testNoTaxiException2() {	
		user0.takeTaxi(taxiInTheCity);
	}
}
