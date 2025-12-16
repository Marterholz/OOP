import java.time.Year;
import java.util.Scanner;

public class Book {

    private final int id;
    private static int idGen = 0;
    private String title;
    private String author;
    private int year;
    private boolean available;

    public Book() {
        this.id = ++idGen;
        this.title = "Unknown";
        this.author = "Unknown";
        this.year = Year.now().getValue();
        this.available = true;
    }

    public Book(String title, String author, int year) {
        this();
        setTitle(title);
        setAuthor(author);
        setYear(year);
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public boolean isAvailable() { return available; }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be empty.");
        }
        this.title = title.trim();
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author must not be empty.");
        }
        this.author = author.trim();
    }

    public void setYear(int year) {
        final int minYear = 1500;
        final int currentYear = Year.now().getValue();

        if (year < minYear || year > currentYear) {
            throw new IllegalArgumentException("Year must be >1500: " + minYear + " <= Year <= " + currentYear);
        }
        this.year = year;
    }

    public void markAsBorrowed() {
        this.available = false;
    }

    public void markAsReturned() {
        this.available = true;
    }

    @Override
    public String toString() {
        String status = available ? "Available" : "Borrowed";
        return "Book [ID=" + id + ", Title='" + title + "', Author='" + author + "', Year=" + year + ", Status=" + status + "]";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputTitle = "";
        String inputAuthor = "";
        int inputYear = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter Title: ");
                inputTitle = scanner.nextLine();
                validInput = true;
                if (inputTitle.trim().isEmpty()) throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Title cannot be empty.");
                validInput = false;
            }
        }
        validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter Author: ");
                inputAuthor = scanner.nextLine();
                validInput = true;
                if (inputAuthor.trim().isEmpty()) throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Author cannot be empty.");
                validInput = false;
            }
        }
        validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter Year (e.g., 2024): ");
                inputYear = scanner.nextInt();
                validInput = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Enter a numeric year.");
                scanner.next();
            }
        }
        scanner.close();

        try {
            Book book = new Book(inputTitle, inputAuthor, inputYear);
            System.out.println("\n Book Created ");
            System.out.println(book);

            System.out.println("\n Marking book as borrowed...");
            book.markAsBorrowed();
            System.out.println(book);

        } catch (IllegalArgumentException e) {
            System.out.println("\n Creation Error: " + e.getMessage());
        }
    }
}