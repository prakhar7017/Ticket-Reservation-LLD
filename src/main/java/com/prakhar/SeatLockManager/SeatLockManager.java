package com.prakhar.SeatLockManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.prakhar.Seat.Seat;
import com.prakhar.enums.SeatStatus;

public class SeatLockManager {
    private final Map<String, Long>lockExpiryMap = new ConcurrentHashMap<>();
    private final long lockTimeoutMillis= 30000;

    public boolean lockSeat(Seat seat){
        if(seat.lockSeat()){
            seat.setSeatStatus(SeatStatus.LOCKED);;
            lockExpiryMap.put(seat.getSeatId(), System.currentTimeMillis() + lockTimeoutMillis);
            return true;
        }
        return false;
    }

    public void unlockSeat(Seat seat){
        seat.setSeatStatus(SeatStatus.AVAILABLE);
        lockExpiryMap.remove(seat.getSeatId());
        seat.unlockSeat();
    }

    public boolean isSeatLocked(Seat seat){
        Long expiry = this.lockExpiryMap.get(seat.getSeatId());
        if(expiry == null) return false;
        if(System.currentTimeMillis() > expiry){
            this.unlockSeat(seat);
            return false;
        }
        return true;
    }
}
