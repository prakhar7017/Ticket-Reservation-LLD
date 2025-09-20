package com.prakhar.shows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.prakhar.Seat.Seat;
import com.prakhar.enums.SeatStatus;
import com.prakhar.movie.Movie;
import com.prakhar.screen.Screen;

public class Show {
    private String showId;
    private Movie movie;
    private Screen screen;
    private Date showTime;
    private List<Seat> seats = new ArrayList<>();

    public Show(String showId, Movie movie, Screen screen, Date showTime, int seatCount) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.showTime = showTime;

        for(int i=1; i<=seatCount; i++){
            seats.add(new Seat("SEAT-"+i, "R1", i));
        }
    }

    public String getShowId() {
        return showId;
    }

    public List<Seat> getSeats(){
        return this.seats;
    }

    public List<Seat> getAvailableSeats(){
        List<Seat> availableSeats = new ArrayList<>(); 
        for(Seat _seat:this.seats){
            if(_seat.getSeatStatus()==SeatStatus.AVAILABLE){
                availableSeats.add(_seat);
            }
        }   
        return availableSeats;
    }

    public Movie getMovie() {
        return this.movie;
    }
    
    public Date getStartTime() {
        return this.showTime;
    }
}
