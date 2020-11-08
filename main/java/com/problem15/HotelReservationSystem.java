package com.problem15;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
			String hotelName = sc.next();
			System.out.print("Enter Weekday Rate: $");
			int weekdayRate = sc.nextInt();
			System.out.print("Enter Weekend Rate: $");
			int weekendRate = sc.nextInt();
			Hotel hotelObj = new Hotel(hotelName, weekdayRate, weekendRate);
			hotelList.add(hotelObj);
			System.out.println("Do you want to add more Hotels(Y/N or y/n)");
			choice = sc.next().charAt(0);
			sc.nextLine();
		}while(choice == 'Y' || choice == 'y');
	}
	    
	public static void getCheapestHotel() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("ddMMMyyyy");
		System.out.println("Enter the Start Date: ");
		String start=sc.next();
		System.out.println("Enter the End Date: ");
		String end=sc.next();
		LocalDate startDate = LocalDate.parse(start, dateFormat);
		LocalDate endDate = LocalDate.parse(end, dateFormat);
		final DayOfWeek startWeek = startDate.getDayOfWeek();
		final DayOfWeek endWeek = endDate.getDayOfWeek();
		long days = ChronoUnit.DAYS.between(startDate, endDate);
		long daysInWeekday = days - 2 * ((days + startWeek.getValue())/7);
		long weekdays= daysInWeekday + (startWeek == DayOfWeek.SUNDAY ? 1 : 0) + (endWeek == DayOfWeek.SUNDAY ? 1 : 0);
		long weekend= days-daysInWeekday;
		int minimumCost = (int) (hotelList.get(0).weekdayRate*weekdays + hotelList.get(0).weekendRate*weekend);
	}
		
	public static void main( String[] args ) {
		System.out.println("Welcome to Hotel Reservation Program");
	    System.out.println("Adding a Hotel:");        
		addHotel();
		System.out.println("Enter dates[Example: 20NOV2020] for finding cheapest hotel: ");
	    getCheapestHotel();
	    System.out.println(hotelList);
	}
}