package com.hotel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class BookingServiceTest {
    private BookingService service;

    @Before
    public void setUp() {
        service = new BookingService();
        service.addRoom(new Room(101, "Deluxe"));
        service.addRoom(new Room(102, "Suite"));
    }

    @Test
    public void testBookRoomSuccess() {
        assertTrue("Booking should succeed", service.bookRoom(101));
    }

    @Test
    public void testDoubleBookingFails() {
        service.bookRoom(101);
        assertFalse("Double booking should fail", service.bookRoom(101));
    }

    @Test
    public void testCancelBooking() {
        service.bookRoom(102);
        assertTrue("Cancel should succeed", service.cancelBooking(102));
    }

    @Test
    public void testRoomAvailability() {
        assertTrue("Room should be available", service.isAvailable(101));
        service.bookRoom(101);
        assertFalse("Room should be unavailable", service.isAvailable(101));
    }

    @Test
    public void testBookNonExistentRoom() {
        assertFalse("Booking non-existent room should fail", service.bookRoom(999));
    }
}