package com.hotel;

import java.util.HashMap;
import java.util.Map;

public class BookingService {
    private Map<Integer, Room> rooms = new HashMap<>();

    public void addRoom(Room room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public boolean bookRoom(int roomNumber) {
        Room room = rooms.get(roomNumber);
        if (room == null) {
            System.out.println("Room " + roomNumber + " does not exist.");
            return false;
        }
        if (room.isBooked()) {
            System.out.println("Room " + roomNumber + " already booked!");
            return false;
        }
        room.setBooked(true);
        System.out.println("Room " + roomNumber + " booked successfully.");
        return true;
    }

    public boolean cancelBooking(int roomNumber) {
        Room room = rooms.get(roomNumber);
        if (room != null && room.isBooked()) {
            room.setBooked(false);
            System.out.println("Booking cancelled for room " + roomNumber);
            return true;
        }
        System.out.println("Cannot cancel - room not booked.");
        return false;
    }

    public boolean isAvailable(int roomNumber) {
        Room room = rooms.get(roomNumber);
        return room != null && !room.isBooked();
    }
}