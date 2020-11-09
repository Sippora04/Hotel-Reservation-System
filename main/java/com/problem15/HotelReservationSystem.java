package com.problem15;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HotelReservationSystem {
	static Scanner sc=new Scanner(System.in);
	public static List<Hotel> hotelList = new ArrayList<>();
		
	public static void addHotel() {
		char choice;
		do {
			System.out.println("Enter Hotel Name:");
			String hotelName = sc.next();
			System.out.print("Enter Weekday Rate: $");
			int weekdayRate = sc.nextInt();
			System.out.print("Enter Weekend Rate: $");
			int weekendRate = sc.nextInt();
			System.out.print("Enter Hotel Rating: $");
			int hotelRating = sc.nextInt();
			Hotel hotelObj = new Hotel(hotelName, weekdayRate, weekendRate, hotelRating);
			hotelList.add(hotelObj);
			System.out.println("Do you want to add more Hotels(Y/N or y/n)");
			choice = sc.next().charAt(0);
			sc.nextLine();
		}while(choice == 'Y' || choice == 'y');
	}
	    
	public static void getCheapestHotel() {
		Date startDate = null;
		Date endDate = null;
		System.out.println("Enter Start Date :");
		String start = sc.next();
		System.out.println("Enter End date :");
		String end = sc.next();
		try {
			startDate = new SimpleDateFormat("dd/MM/yyyy").parse(start);
			endDate = new SimpleDateFormat("dd/MM/yyyy").parse(end); 
		}	catch (ParseException e) {
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
		for(Hotel hotel: hotelList) {
			long totalCost = noOfWeekdays * hotel.getWeekdayRate() + noOfWeekends * hotel.getWeekendRate();
	        hotel.setTotalCost((int) totalCost);
	        if(minimumCost == 0)
	        	minimumCost = hotel.getTotalCost();
	        if(hotel.getTotalCost() < minimumCost)
	        	minimumCost = hotel.getTotalCost();
	    }
		List<String> cheapestListOfHotelName = new ArrayList<>();
		 for(int i = 0; i < hotelList.size(); i++) {
			 if(hotelList.get(i).getTotalCost() == minimumCost)
				 cheapestListOfHotelName.add(hotelList.get(i).getHotelName());
		 }
		 System.out.println("\nCheapest Hotel is: " + cheapestListOfHotelName + " with total cost $"+minimumCost);
	}
		
	public static void main( String[] args ) {
		System.out.println("Welcome to Hotel Reservation Program");
	    System.out.println("Adding a Hotel:");        
		addHotel();
		System.out.println("Enter dates[Example: 20NOV2020] for finding cheapest hotel: ");
	    getCheapestHotel();
	}
}