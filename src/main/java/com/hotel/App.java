package com.hotel;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Hotel Room Booking System ===");
        
        BookingService service = new BookingService();
        service.addRoom(new Room(101, "Deluxe"));
        service.addRoom(new Room(102, "Suite"));
        service.addRoom(new Room(103, "Standard"));

        service.bookRoom(101);
        service.bookRoom(101); // Duplicate booking test
        service.bookRoom(102);
        service.cancelBooking(101);
        
        System.out.println("Room 101 available? " + service.isAvailable(101));
        System.out.println("Room 102 available? " + service.isAvailable(102));
    }
}