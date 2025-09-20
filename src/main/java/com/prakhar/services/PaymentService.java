package com.prakhar.services;

import java.util.UUID;

import com.prakhar.Booking.Booking;
import com.prakhar.payment.Payment;

public class PaymentService {
    public Payment processPayment(Booking booking, double amount, boolean isSuccess) {
        Payment payment = new Payment(UUID.randomUUID().toString(), amount, booking);
        payment.completePayment(isSuccess);
        return payment;
    }
}
