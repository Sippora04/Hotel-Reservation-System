package com.problem15;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotelReservationSystem {
	  static Scanner sc=new Scanner(System.in);
		public static List<Hotel> hotelList = new ArrayList<>();
		
		public static void addHotel() {
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
	    
		public static void getCheapestHotel() {
			Date startDate = null;
			Date endDate = null;
			System.out.println("Enter the Start Date: ");
			String start = sc.next();
			System.out.println("Enter the End Date: ");
			String end = sc.next();
			try {
				startDate = new SimpleDateFormat("ddMMMyyyy").parse(start);
				endDate = new SimpleDateFormat("ddMMMyyyy").parse(end); 
			} catch (ParseException e) {
					e.printStackTrace();
				} 
			long numberOfDays = (endDate.getTime() - startDate.getTime())/1000/60/60/24;
			int minimumCost = hotelList.get(0).getRegularCustomerRate();
			String cheapestHotel = hotelList.get(0).getHotelName();
			for(int i = 1; i < hotelList.size(); i++) 
				if(hotelList.get(i).getRegularCustomerRate() < minimumCost) {
					minimumCost = hotelList.get(i).getRegularCustomerRate();
					cheapestHotel = hotelList.get(i).getHotelName();
				}
				System.out.println("Hotel: " + cheapestHotel + ", Total Cost: $" + minimumCost * numberOfDays);
			}
		
		public static void main( String[] args ) {
	    	System.out.println("Welcome to Hotel Reservation Program");
	    	System.out.println("Adding a Hotel:");        
			addHotel();
			System.out.println("Enter dates[Example: 20NOV2020] for finding cheapest hotel: ");
	        getCheapestHotel();
	    }
}