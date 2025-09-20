package com.prakhar.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.prakhar.Booking.Booking;
import com.prakhar.Seat.Seat;
import com.prakhar.enums.PaymentStatus;
import com.prakhar.movie.Movie;
import com.prakhar.payment.Payment;
import com.prakhar.screen.Screen;
import com.prakhar.services.BookingService;
import com.prakhar.services.PaymentService;
import com.prakhar.shows.Show;
import com.prakhar.theater.Theater;
import com.prakhar.user.User;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Movie movie1 = new Movie("M1", "Raone","120");
        Movie movie2 = new Movie("M2", "Kung Fu Panda","100");
        Movie movie3 = new Movie("M3", "Hathi Mere Sathi","180");

        Theater theater1 = new Theater("T1", "PVR","Delhi");
        Theater theater2 = new Theater("T2", "INOX","Mumbai");

        Screen screen1 = new Screen("S1", "Screen 1",100);
        Screen screen2 = new Screen("S2", "Screen 2",100);
        Screen screen3 = new Screen("S1", "Screen 1",100);

        theater1.addScreen(screen1);
        theater1.addScreen(screen2);
        theater2.addScreen(screen3);

        Show show1 = new Show("Sh1",movie1 ,screen1 , new java.util.Date(),20);
        Show show2 = new Show("Sh2",movie2 ,screen2 , new java.util.Date(),50);
        Show show3 = new Show("Sh1",movie3 ,screen3 , new java.util.Date(),70);

        screen1.addShow(show1);
        screen2.addShow(show2);
        screen3.addShow(show3);

        BookingService bookingService = new BookingService();
        PaymentService paymentService = new PaymentService();

        // Step 1: User details
        System.out.println("Welcome to Ticket Booking System!");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        User user = new User(UUID.randomUUID().toString(), name, email);

         // Step 2: Show available movies
        System.out.println("\nAvailable Movies:");
        System.out.println("1. " + movie1.getTitle());
        System.out.println("2. " + movie2.getTitle());
        System.out.println("3. " + movie3.getTitle());
        System.out.print("Select movie (1 , 2 or 3): ");
        int movieChoice = scanner.nextInt();
        Movie selectedMovie ;
        if(movieChoice == 1){
            selectedMovie = movie1;
        } else if(movieChoice == 2){
            selectedMovie = movie2;
        } else {
            selectedMovie = movie3;
        }

        // Step 3: Select show
        System.out.println("\nAvailable Shows for " + selectedMovie.getTitle() + ":");
        List<Show> shows = new ArrayList<>();
        
        // Check all screens for shows with the selected movie
        shows.addAll(screen1.getShowsByMovie(selectedMovie));
        shows.addAll(screen2.getShowsByMovie(selectedMovie));
        shows.addAll(screen3.getShowsByMovie(selectedMovie));
        
        if (shows.isEmpty()) {
            System.out.println("No shows available for this movie.");
            return;
        }
        
        for (int i = 0; i < shows.size(); i++) {
            System.out.println((i + 1) + ". Show ID: " + shows.get(i).getShowId() + " | Start Time: " + shows.get(i).getStartTime());
        }
        System.out.print("Select show number: ");
        int showChoice = scanner.nextInt();
        
        if (showChoice < 1 || showChoice > shows.size()) {
            System.out.println("Invalid show selection.");
            return;
        }
        
        Show selectedShow = shows.get(showChoice - 1);

         // Step 4: Select seat
        System.out.println("\nAvailable Seats:");
        List<Seat> availableSeats = selectedShow.getAvailableSeats();
        for (int i = 0; i < availableSeats.size(); i++) {
            System.out.println((i + 1) + ". " + availableSeats.get(i).getSeatId());
        }
        System.out.print("Select seat number: ");
        int seatChoice = scanner.nextInt();
        Seat chosenSeat = availableSeats.get(seatChoice - 1);

        // Step 5: Create booking (seat lock)
        Booking booking;
        try {
            booking = bookingService.createBooking(user, selectedShow, Arrays.asList(chosenSeat));
            System.out.println("\nSeat locked! Please proceed to payment.");
        } catch (RuntimeException e) {
            System.out.println("Booking failed: " + e.getMessage());
            return;
        }

        // Step 6: Payment
        System.out.print("\nEnter amount to pay: ");
        double amount = scanner.nextDouble();
        System.out.print("Confirm payment? (y/n): ");
        char payChoice = scanner.next().charAt(0);

        Payment payment = paymentService.processPayment(booking, amount, (payChoice == 'y'));

        // Step 7: Print ticket
        if (payment.getStatus() == PaymentStatus.COMPLETED) {
            System.out.println("\n Payment Successful!");
            System.out.println("Ticket Generated:");
            System.out.println("Name: " + user.getName());
            System.out.println("Movie: " + selectedMovie.getTitle());
            System.out.println("Theatre: " + theater1.getName());
            System.out.println("Show: " + selectedShow.getShowId() + " | " + selectedShow.getStartTime());
            System.out.println("Seat: " + chosenSeat.getSeatId());
            System.out.println("Booking Status: " + booking.getStatus());
        } else {
            System.out.println("\n Payment Failed. Booking Cancelled.");
        }
    }
}
