import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
class Contact implements Serializable {
    private String phone;
    private String email;
    public Contact() {}
    public Contact(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    public void set(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void display() {
        System.out.println("Contact Information:");
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
    }
}

class Address implements Serializable {
    private int street;
    private int house;
    private String city;

    public Address() {
        street = 0;
        house = 0;
        city = "";
    }

    public Address(int street, int house, String city) {
        this.street = street;
        this.house = house;
        this.city = city;
    }
    public void set(int street, int house, String city) {
        this.street = street;
        this.house = house;
        this.city = city;
    }
    public int getStreet() {
        return street;
    }
    public int getHouse() {
        return house;
    }
    public String getCity() {
        return city;
    }
    public void display() {
        System.out.println("Street No: " + street);
        System.out.println("House No: " + house);
        System.out.println("City Name: " + city);
    }
}
class Persons implements Serializable {
    private String name;
    private Address address;
    private Contact contact;
    public Persons() {}
    public Persons(String name, Address address, Contact contact) {
        this.name = name;
        this.address = address;
        this.contact = contact;
    }
    public void set(String name, Address address, Contact contact) {
        this.name = name;
        this.address = address;
        this.contact = contact;
    }
    public String getName() {
        return name;
    }
    public Address getAddress() {
        return address;
    }
    public Contact getContact() {
        return contact;
    }
    public void display() {
        System.out.println("Name: " + name);
        address.display();
        contact.display();
    }
}
class Guest extends Persons {
    public Guest(String name, Address address, Contact contact) {
        super(name, address, contact);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public void displayInfo() {
        super.display();
    }
    public void checkIn() {
        System.out.println(super.getName() + " has checked in.");
    }
    public void checkOut() {
        System.out.println(super.getName() + " has checked out.");
    }
}
class Hotel implements Serializable {
    private String name;
    private Address address;
    private Contact contact;
    private int rooms;

    public Hotel() {}

    public Hotel(String name, Address address, Contact contact, int rooms) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.rooms = rooms;
    }

    public void setHotelData(String name, Address address, Contact contact, int rooms) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Contact getContact() {
        return contact;
    }

    public int getRooms() {
        return rooms;
    }

    public void displayInfo() {
        System.out.println("Hotel Information:");
        System.out.println("Name: " + name);
        address.display();
        contact.display();
        System.out.println("Rooms: " + rooms);
    }
}

class Room implements Serializable {
    private int roomNumber;
    private boolean availability;

    public Room() {}

    public Room(int roomNumber, boolean availability) {
        this.roomNumber = roomNumber;
        this.availability = availability;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean checkAvailability() {
        return availability;
    }

    public void setRoomData(int roomNumber, boolean availability) {
        this.roomNumber = roomNumber;
        this.availability = availability;
    }

    public void displayInfo() {
        System.out.println("Room Information:");
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room available: " + availability);
    }
}

class Billing implements Serializable {
    private int roomsReserved;
    private int days;
    private double ratePerRoomPerDay;

    public Billing(int roomsReserved, int days, double ratePerRoomPerDay) {
        this.roomsReserved = roomsReserved;
        this.days = days;
        this.ratePerRoomPerDay = ratePerRoomPerDay;
    }

    public double calculateBill() {
        return roomsReserved * days * ratePerRoomPerDay;
    }

    public void displayBillingDetails() {
        double totalAmount = calculateBill();
        System.out.println("Billing Details:");
        System.out.println("Rooms reserved: " + roomsReserved);
        System.out.println("Days: " + days);
        System.out.println("Rate per room per day: $" + ratePerRoomPerDay);
        System.out.println("Total Amount: $" + totalAmount);
    }
}
abstract class AbstractReservation implements Serializable {
    protected Guest guest;
    protected int reservationNo;
    protected Room[] rooms;
    protected LocalDate checkInDate;
    protected LocalDate checkOutDate;
    protected Billing bill;
    protected double totalBill;

    public AbstractReservation(Guest guest, Room[] rooms, LocalDate checkInDate, LocalDate checkOutDate, double ratePerRoomPerDay) {
        this.guest = guest;
        this.rooms = rooms;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        int stayDuration = getStayDuration();
        this.bill = new Billing(rooms.length, stayDuration, ratePerRoomPerDay);
        this.totalBill = bill.calculateBill();
    }

    public Guest getGuest() {
        return guest;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public int getReservationNo() {
        return reservationNo;
    }

    public void setReservationNo(int reservationNo) {
        this.reservationNo = reservationNo;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public int getStayDuration() {
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    public Billing getBill() {
        return bill;
    }

    public abstract void displayReservationDetails();
}

class Reservation extends AbstractReservation {

    public Reservation(Guest guest, Room[] rooms, LocalDate checkInDate, LocalDate checkOutDate, double ratePerRoomPerDay) {
        super(guest, rooms, checkInDate, checkOutDate, ratePerRoomPerDay);
    }

    @Override
    public void displayReservationDetails() {
        System.out.println("Reservation Details:");
        System.out.println("Guest Name: " + guest.getName());
        System.out.println("Reservation No: " + reservationNo);
        for (Room room : rooms) {
            System.out.println("Room No: " + room.getRoomNumber());
        }
        System.out.println("Check-in Date: " + checkInDate);
        System.out.println("Check-out Date: " + checkOutDate);
        System.out.println("Stay Duration: " + getStayDuration() + " days");
        bill.displayBillingDetails();
    }
}
class Manager {
    private String password;

    public Manager() {
        password = "manager123";
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("--Welcome to Hotel Reservation System--");
        System.out.println("--Reserve your room--");
        System.out.println("Enter your name--");
        String n = input.next();
        System.out.println("Enter your address (street, Hno, City) :--");
        int street = input.nextInt();
        int house = input.nextInt();
        String city = input.next();
        System.out.println("Enter your contact info (Phone No, Email):--");
        String ph = input.next();
        String mail = input.next();

        Address address = new Address(street, house, city);
        Contact contact = new Contact(ph, mail);
        Guest guest = new Guest(n, address, contact);
        Room[] rooms = {
                new Room(101, true),
                new Room(102, true)
        };

        LocalDate checkInDate = LocalDate.of(2023, 5, 20);
        LocalDate checkOutDate = LocalDate.of(2023, 5, 25);
        double ratePerRoomPerDay = 100.0;

        Reservation reservation = new Reservation(guest, rooms, checkInDate, checkOutDate, ratePerRoomPerDay);
        reservation.setReservationNo(101);
        reservation.displayReservationDetails();
    }
}