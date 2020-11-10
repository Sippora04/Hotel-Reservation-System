package com.problem15;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HotelReservationSystem {
	static Scanner sc = new Scanner(System.in);
	public static List<Hotel> hotelList = new ArrayList<>();
		
	public static void addHotel() {
		char choice;
		System.out.println("Enter Hotel details \n");        			
		do {
			System.out.println("Enter Hotel Name:");
			String hotelName = sc.next();
			System.out.print("Enter Weekday Rate: $");
			int weekdayRate = sc.nextInt();
			System.out.print("Enter Weekend Rate: $");
			int weekendRate = sc.nextInt();
			System.out.print("Enter Hotel Rating: ");
			int hotelRating = sc.nextInt();
			System.out.print("Enter Weekday rate for Reward Customer: ");
			int weekdayRewardRate = sc.nextInt();
			System.out.print("Enter Weekdend rate for Reward Customer: ");
			int weekendRewardRate = sc.nextInt();
			Hotel hotelObj = new Hotel(hotelName, weekdayRate, weekendRate, hotelRating, weekdayRewardRate, weekendRewardRate);
			hotelList.add(hotelObj);
			System.out.println("Do you want to add more Hotels(Y/N or y/n)");
			choice = sc.next().charAt(0);
			sc.nextLine();
		}while(choice == 'Y' || choice == 'y');
	}
	    
	public static void getCheapestHotel(int choice) throws EntryInvalidException {
		Date startDate = null;
		Date endDate = null;
		System.out.println("Enter Start Date :");
		String start = sc.next();
		System.out.println("Enter End date :");
		String end = sc.next();
		try {
			startDate = new SimpleDateFormat("dd/MM/yyyy").parse(start);
			endDate = new SimpleDateFormat("dd/MM/yyyy").parse(end); 
		} catch (ParseException e) {
				e.printStackTrace();
			} 
		long noOfDays = 1 + (endDate.getTime() - startDate.getTime())/1000/60/60/24;
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		Calendar endcalendar = Calendar.getInstance();
		endcalendar.setTime(endDate);
		long noOfWeekdays = 0;
		while(startCalendar.getTimeInMillis() <= endcalendar.getTimeInMillis()) {
			if((startCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY ) && (startCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY )) {
				noOfWeekdays++;
			}
			startCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		long noOfWeekends = noOfDays - noOfWeekdays;
		long minimumCost = 0;
		switch(choice) {
		case 1:	for(Hotel hotel: hotelList) {
					long totalCost = noOfWeekdays * hotel.getWeekdayRate() + noOfWeekends * hotel.getWeekendRate();
			        hotel.setTotalCost((int) totalCost);
			        if(minimumCost == 0)
			        	minimumCost = hotel.getTotalCost();
			        if(hotel.getTotalCost() < minimumCost)
			        	{minimumCost = hotel.getTotalCost();}
			     }
				break;
		case 2: for(Hotel hotel: hotelList) {
					long totalCost = noOfWeekdays * hotel.getWeekdayRate() + noOfWeekends * hotel.getWeekendRate();
			        hotel.setTotalCost((int) totalCost);
			        if(minimumCost == 0)
			        	minimumCost = hotel.getTotalCost();
			        if(hotel.getTotalCost() < minimumCost)
			        	{minimumCost = hotel.getTotalCost();}
			     }
				 break;
		default: System.out.println("Invlaid");
		}
		List<Hotel> cheapestListOfHotelName=new ArrayList<Hotel>();
		for(int i = 0; i < hotelList.size(); i++) {
			if(hotelList.get(i).getTotalCost() == minimumCost)
				cheapestListOfHotelName.add(hotelList.get(i));
		}
		Hotel cheapestBestRatedHotel = cheapestListOfHotelName.stream().max((hotelOne, hotelTwo) -> hotelOne.getHotelRating() - hotelTwo.getHotelRating()).orElse(null);
		String cheapestAndHighestRatedHotel = cheapestBestRatedHotel.getHotelName();
		System.out.println(cheapestAndHighestRatedHotel + ", Rating: " + cheapestBestRatedHotel.getHotelRating() + " and Total Rate :$" + minimumCost);
	}
	
	public static String getBestRatedHotel() {
		Date startDate = null;
		Date endDate = null;
		System.out.println("Enter Start Date :");
		String start = sc.next();
		System.out.println("Enter End date :");
		String end = sc.next();
		try {
			startDate = new SimpleDateFormat("dd/MM/yyyy").parse(start);
			endDate = new SimpleDateFormat("dd/MM/yyyy").parse(end); 
		} catch (ParseException e) {
				e.printStackTrace();
			} 
		long noOfDays = 1 + (endDate.getTime() - startDate.getTime())/1000/60/60/24;
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		Calendar endcalendar = Calendar.getInstance();
		endcalendar.setTime(endDate);
		long noOfWeekdays = 0;
		while(startCalendar.getTimeInMillis() <= endcalendar.getTimeInMillis()) {
			if((startCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY ) && (startCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY )) {
				noOfWeekdays++;
			}
			startCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		long noOfWeekends = noOfDays - noOfWeekdays;
		int maximumRating = 0;
		int count = 0;
		for(Hotel hotel: hotelList) {
			long totalCost = noOfWeekdays * hotel.getWeekdayRate() + noOfWeekends * hotel.getWeekendRate();
	        hotel.setTotalCost(totalCost);
	        if(maximumRating == 0)
	        	maximumRating = hotel.getHotelRating();
	        if(hotel.getHotelRating() > maximumRating)
	        	maximumRating = hotel.getHotelRating();
	    }
		List<String> bestRatedHotelNameList = new ArrayList<>();
		for(int i = 0; i < hotelList.size(); i++) {
			if(hotelList.get(i).getHotelRating() == maximumRating) {
				count = i;
				bestRatedHotelNameList.add(hotelList.get(i).getHotelName());
			}
		}
		System.out.println("Highest Rated Hotel: " + bestRatedHotelNameList + ", Rating: " + maximumRating + " and Total Rate $" + hotelList.get(count).getTotalCost());
		return null;
	}
	
	public int countNoOfHotels() {
		return hotelList.size();
	}
	
	public static void main( String[] args ) {
		System.out.println("Welcome to Hotel Reservation Program");
		addHotel();
		Customer customerObj = new Customer();
		System.out.println("\nEnter the Customer Type for:\n(1) Regular Customer\n(2) Reward Customer");
        int choice = sc.nextInt();
        if(choice == 1)
        	customerObj.setCustomerType("Regular");
        else
        	customerObj.setCustomerType("Reward");
		System.out.println("Enter dates[Example: 09/11/2020] for finding best rated cheapest hotel: ");
	    try {
	    	getCheapestHotel(choice);
	    } catch (EntryInvalidException e) {
	    	e.printStackTrace();
	    }
	    System.out.println("\n Enter dates[Example: 12/11/2020] to finding best rated hotel: ");
	    getBestRatedHotel();
	}
}