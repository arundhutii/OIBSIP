//TASK 1 OIBSIP
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineReservationSystem {
    private Map<String, String> users; // Store username-password pairs
    private Map<String, String> reservations; // Store reservation data
    
    public OnlineReservationSystem() {
        users = new HashMap<>();
        reservations = new HashMap<>();
    }
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
            
            System.out.println();
        }
    }
    
    private void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Login successful.");
            reservationMenu(scanner, username);
        } else {
            System.out.println("Invalid username or password.");
        }
    }
    
    private void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Try again.");
            return;
        }
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users.put(username, password);
        System.out.println("Registration successful. You can now log in.");
    }
    
    private void reservationMenu(Scanner scanner, String username) {
        while (true) {
            System.out.println("1. Make a reservation");
            System.out.println("2. Cancel a reservation");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    makeReservation(scanner, username);
                    break;
                case 2:
                    cancelReservation(scanner, username);
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
            
            System.out.println();
        }
    }
    
    private void makeReservation(Scanner scanner, String username) {
        System.out.print("Enter reservation details: ");
        String reservationDetails = scanner.nextLine();
        
        if (reservations.containsKey(username)) {
            System.out.println("You already have a reservation. Cancel it first to make a new one.");
            return;
        }
        
        reservations.put(username, reservationDetails);
        System.out.println("Reservation created successfully.");
    }
    
    private void cancelReservation(Scanner scanner, String username) {
        if (reservations.containsKey(username)) {
            System.out.println("Your current reservation: " + reservations.get(username));
            System.out.print("Do you want to cancel this reservation? (Y/N): ");
            String confirmation = scanner.nextLine();
            
            if (confirmation.equalsIgnoreCase("Y")) {
                reservations.remove(username);
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Reservation not cancelled.");
            }
        } else {
            System.out.println("You don't have any reservations.");
        }
    }
    
    public static void main(String[] args) {
        OnlineReservationSystem system = new OnlineReservationSystem();
        system.run();
    }
}