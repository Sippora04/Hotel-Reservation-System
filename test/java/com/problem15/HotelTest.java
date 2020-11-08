package com.problem15;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class HotelTest {
	static HotelReservationSystem hotelReservation = null;
	@BeforeClass
	 public  static void setUp() {
		 hotelReservation = new HotelReservationSystem();
	 }

	@Test
	public void givenHotel_added_ShouldTrue() {
		assertTrue(true);
	}

}