package com.prakhar.payment;

import com.prakhar.Booking.Booking;
import com.prakhar.enums.PaymentStatus;

public class Payment {
    private String paymentId;
    private double amount;
    private Booking booking;
    private PaymentStatus status = PaymentStatus.PENDING;

    public Payment(String paymentId, double amount, Booking booking) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.booking = booking;
    }

    public void completePayment(boolean isSuccess) {
        if (isSuccess) {
            this.status = PaymentStatus.COMPLETED;
            booking.confirmBooking();
        }else {
            this.status = PaymentStatus.FAILED;
            booking.cancelBooking();
        }
    }

    public PaymentStatus getStatus(){
        return this.status;
    }
}
