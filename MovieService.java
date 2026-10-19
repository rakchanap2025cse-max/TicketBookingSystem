import java.sql.*;
import java.util.Scanner;
public class MovieService {
    Connection con;
    Scanner sc = new Scanner(System.in);
    public MovieService() { con = DBConnection.getConnection(); }
    public void viewMovies() {
        try {
            if(con == null) { con = DBConnection.getConnection(); if(con==null) return; }
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM movies");
            System.out.println("\n========== AVAILABLE MOVIES ==========");
            System.out.printf("%-5s %-25s %-10s\n", "ID", "Title", "Seats Left");
            while (rs.next()) {
                System.out.printf("%-5d %-25s %-10d\n", rs.getInt("id"), rs.getString("title"), rs.getInt("seats"));
            }
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }
    public void searchMovie() {
        try {
            System.out.print("Enter movie name: ");
            String name = sc.nextLine();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM movies WHERE title LIKE ?");
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("title") + " - Seats: " + rs.getInt("seats"));
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }
    public void bookTicket() {
        try {
            viewMovies();
            System.out.print("Enter Movie ID: "); int movieId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Your Name: "); String customer = sc.nextLine();
            System.out.print("Enter Seats: "); int seats = Integer.parseInt(sc.nextLine());
            PreparedStatement check = con.prepareStatement("SELECT seats FROM movies WHERE id=?");
            check.setInt(1, movieId);
            ResultSet rs = check.executeQuery();
            if (rs.next() && rs.getInt("seats") >= seats) {
                PreparedStatement ps = con.prepareStatement("INSERT INTO bookings(movie_id, customer_name, seats_booked) VALUES(?,?,?)");
                ps.setInt(1, movieId); ps.setString(2, customer); ps.setInt(3, seats); ps.executeUpdate();
                PreparedStatement upd = con.prepareStatement("UPDATE movies SET seats = seats - ? WHERE id=?");
                upd.setInt(1, seats); upd.setInt(2, movieId); upd.executeUpdate();
                System.out.println("*** BOOKING SUCCESSFUL! ***");
            } else { System.out.println("Not enough seats or invalid ID!"); }
        } catch (Exception e) { System.out.println("Booking failed: " + e.getMessage()); }
    }
    public void viewBookings() {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT b.id, m.title, b.customer_name, b.seats_booked FROM bookings b JOIN movies m ON b.movie_id = m.id");
            System.out.printf("%-5s %-15s %-15s %-5s\n", "BID", "Movie", "Customer", "Seats");
            while (rs.next()) {
                System.out.printf("%-5d %-15s %-15s %-5d\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }
    public void cancelBooking() {
        try {
            viewBookings();
            System.out.print("Enter Booking ID to cancel: ");
            int bid = Integer.parseInt(sc.nextLine());
            PreparedStatement get = con.prepareStatement("SELECT movie_id, seats_booked FROM bookings WHERE id=?");
            get.setInt(1, bid); ResultSet rs = get.executeQuery();
            if (rs.next()) {
                int movieId = rs.getInt("movie_id"); int seats = rs.getInt("seats_booked");
                PreparedStatement del = con.prepareStatement("DELETE FROM bookings WHERE id=?");
                del.setInt(1, bid); del.executeUpdate();
                PreparedStatement upd = con.prepareStatement("UPDATE movies SET seats = seats + ? WHERE id=?");
                upd.setInt(1, seats); upd.setInt(2, movieId); upd.executeUpdate();
                System.out.println("*** Booking ID " + bid + " Cancelled! ***");
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }
}