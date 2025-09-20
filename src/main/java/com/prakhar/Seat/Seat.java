package com.prakhar.Seat;

import java.util.concurrent.locks.ReentrantLock;

import com.prakhar.enums.SeatStatus;

public class Seat {
    private String seatId;
    private String row;
    private int number;
    private SeatStatus seatStatus = SeatStatus.AVAILABLE;

    private final ReentrantLock lock = new ReentrantLock();

    public Seat(String seatId, String row, int number){
        this.seatId = seatId;
        this.row = row;
        this.number = number;
    }

    public boolean lockSeat(){
        return lock.tryLock();
    }

    public void unlockSeat(){
        if(lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    public String getSeatId(){
        return this.seatId;
    }

    public SeatStatus getSeatStatus(){
        return this.seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus){
        this.seatStatus = seatStatus;
    }

    @Override
    public String toString(){
        return "Seat{" +
                "seatId='" + seatId + '\'' +
                ", row='" + row + '\'' +
                ", number=" + number +
                ", seatStatus=" + seatStatus +
                '}';
    }
}
