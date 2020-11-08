package com.problem15;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class HotelReservationSystem {
	  static Scanner sc=new Scanner(System.in);
		public static List<Hotel> hotelList = new ArrayList<>();
		
		public static void addHotel() {
			System.out.println("Adding a Hotel:");        
			char choice;
			do {
				System.out.println("Enter Hotel Name:");
				String hotelName = sc.nextLine();
				System.out.println("Enter the rate for regular customer");
				int regularCustomerRate = sc.nextInt();
				Hotel hotelObj = new Hotel(regularCustomerRate, hotelName);
				hotelList.add(hotelObj);
				System.out.println("Do you want to add more Hotels(Y/N or y/n)");
				choice = sc.next().charAt(0);
				sc.nextLine();
			}while(choice == 'Y' || choice == 'y');
		}
	    public static void main( String[] args ) {
	    	System.out.println("Welcome to Hotel Reservation Program");
		    addHotel();
	    }
}