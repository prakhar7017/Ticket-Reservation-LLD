package com.prakhar.services;

import java.util.List;
import java.util.UUID;

import com.prakhar.Booking.Booking;
import com.prakhar.Seat.Seat;
import com.prakhar.shows.Show;
import com.prakhar.user.User;

public class BookingService {
    public Booking createBooking(User user, Show show, List<Seat> seats){
        return new Booking(UUID.randomUUID().toString(), user, show, seats);
    }
}
