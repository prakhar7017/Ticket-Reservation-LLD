package com.prakhar.Booking;

import java.util.ArrayList;
import java.util.List;

import com.prakhar.Seat.Seat;
import com.prakhar.SeatLockManager.SeatLockManager;
import com.prakhar.enums.BookingStatus;
import com.prakhar.enums.SeatStatus;
import com.prakhar.shows.Show;
import com.prakhar.user.User;

public class Booking {
    private String bookingId;
    private User user;
    private Show show;
    private List<Seat> seats;
    private BookingStatus status = BookingStatus.CREATED;

    private final SeatLockManager seatLockManager = new SeatLockManager();

    public Booking(String bookingId, User user, Show show, List<Seat>seats){
        this.bookingId = bookingId;
        this.user = user;
        this.show = show;
        this.seats = new ArrayList<>();

        for(Seat seat:seats){
            boolean locked = seatLockManager.lockSeat(seat);
            if(!locked){
                releaseAll();
                throw new RuntimeException("Seat " + seat.getSeatId() + " is already locked/booked!");
            }
            this.seats.add(seat);
        }
        user.addBooking(this);
    }

    public synchronized void confirmBooking(){
        this.status = BookingStatus.CONFIRMED;
        for(Seat seat:this.seats){
            seat.setSeatStatus(SeatStatus.BOOKED);
            seat.unlockSeat();
        }
    }

    public synchronized void cancelBooking(){
        this.status=BookingStatus.CANCELLED;
        releaseAll();
    }

    public void releaseAll(){
        for(Seat seat:this.seats){
            seatLockManager.unlockSeat(seat);
        }
    }

    public BookingStatus getStatus(){
        return this.status;    
    }
}
