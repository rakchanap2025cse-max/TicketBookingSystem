import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieService service = new MovieService();
        if(service.con == null) { System.out.println("Fix DB connection!"); return; }
        System.out.println("Connected to Movie Booking System!");
        while (true) {
            System.out.println("\n=== MOVIE TICKET BOOKING SYSTEM ===");
            System.out.println("1. View Movies\n2. Search Movie\n3. Book Ticket\n4. View All Bookings\n5. Cancel Booking\n6. Exit");
            System.out.print("Choose (1-6): ");
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1: service.viewMovies(); break;
                case 2: service.searchMovie(); break;
                case 3: service.bookTicket(); break;
                case 4: service.viewBookings(); break;
                case 5: service.cancelBooking(); break;
                case 6: System.exit(0); break;
            }
        }
    }
}