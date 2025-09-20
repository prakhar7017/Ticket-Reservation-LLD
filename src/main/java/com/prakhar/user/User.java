package com.prakhar.user;

import java.util.ArrayList;
import java.util.List;

import com.prakhar.Booking.Booking;

public class User {
    private String userId;
    private String name;
    private String email;
    private List<Booking> bookings= new ArrayList<>();

    public User(String userId, String name,String email){
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public String getName(){
        return this.name;
    }

    public void addBooking(Booking booking){
        this.bookings.add(booking);
    }
}
