import java.util.*;

class Student {
    int id;
    String name;
    int roomNo;
    int days;

    Student(int id, String name, int roomNo, int days) {
        this.id = id;
        this.name = name;
        this.roomNo = roomNo;
        this.days = days;
    }
}

class Room {
    int roomNo;
    int capacity;
    int occupied;

    Room(int roomNo, int capacity) {
        this.roomNo = roomNo;
        this.capacity = capacity;
        this.occupied = 0;
    }
}

public class HostelManagement {

    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Room> rooms = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Create rooms
        rooms.add(new Room(101, 2));
        rooms.add(new Room(102, 2));
        rooms.add(new Room(103, 3));

        int choice;

        do {
            System.out.println("\n--- Hostel Management System ---");
            System.out.println("1. Allocate Room");
            System.out.println("2. View Students");
            System.out.println("3. Checkout Student");
            System.out.println("4. Search Student");
            System.out.println("5. Room Status");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: allocateRoom(); break;
                case 2: viewStudents(); break;
                case 3: checkout(); break;
                case 4: searchStudent(); break;
                case 5: roomStatus(); break;
            }

        } while (choice != 6);
    }

    static void allocateRoom() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Stay Days: ");
        int days = sc.nextInt();

        for (Room r : rooms) {
            if (r.occupied < r.capacity) {
                r.occupied++;
                students.add(new Student(id, name, r.roomNo, days));
                System.out.println("Room Allocated: " + r.roomNo);
                return;
            }
        }

        System.out.println("No rooms available!");
    }

    static void viewStudents() {
        for (Student s : students) {
            System.out.println("ID: " + s.id + ", Name: " + s.name +
                    ", Room: " + s.roomNo + ", Days: " + s.days);
        }
    }

    static void checkout() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        Iterator<Student> it = students.iterator();

        while (it.hasNext()) {
            Student s = it.next();
            if (s.id == id) {
                for (Room r : rooms) {
                    if (r.roomNo == s.roomNo) {
                        r.occupied--;
                    }
                }
                it.remove();
                System.out.println("Checked out successfully!");
                return;
            }
        }

        System.out.println("Student not found!");
    }

    static void searchStudent() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        for (Student s : students) {
            if (s.id == id) {
                System.out.println("Found: " + s.name +
                        " Room: " + s.roomNo);
                return;
            }
        }

        System.out.println("Student not found!");
    }

    static void roomStatus() {
        for (Room r : rooms) {
            System.out.println("Room " + r.roomNo +
                    " -> Occupied: " + r.occupied +
                    "/" + r.capacity);
        }
    }
}