import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.time.Year;

public class App {

    private List<Book> books;
    private Scanner scanner;
    private final int minYear = 1500;
    private final int currentYear = Year.now().getValue();

    public App() {
        this.books = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("choose1: ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        printAllBooks();
                        break;
                    case 2:
                        addNewBook();
                        break;
                    case 3:
                        searchBooksByTitle();
                        break;
                    case 4:
                        borrowBook();
                        break;
                    case 5:
                        returnBook();
                        break;
                    case 6:
                        deleteBookById();
                        break;
                    case 7:
                        running = false;
                        System.out.println("bye!");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n Welcome to Library! ");
        System.out.println("1. Print all books");
        System.out.println("2. Add new book");
        System.out.println("3. Search books by title");
        System.out.println("4. Borrow a book");
        System.out.println("5. Return a book");
        System.out.println("6. Delete a book by id");
        System.out.println("7. Quit");
    }

    private void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("\n All Books ");
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    private void addNewBook() {
        System.out.println("\n Add New Book ");
        String title = "";
        String author = "";
        int year = 0;

        try {
            System.out.print("Enter Title: ");
            title = scanner.nextLine();
            if (title.trim().isEmpty()) throw new IllegalArgumentException("Title cannot be empty.");

            System.out.print("Enter Author: ");
            author = scanner.nextLine();
            if (author.trim().isEmpty()) throw new IllegalArgumentException("Author cannot be empty.");

            System.out.print("Enter Year (" + minYear + "-" + currentYear + "): ");
            year = scanner.nextInt();
            scanner.nextLine();

            Book newBook = new Book(title, author, year);
            books.add(newBook);
            System.out.println("Book added: " + newBook.getTitle());

        } catch (IllegalArgumentException e) {
            System.out.println("Error adding book: " + e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error");
            scanner.nextLine();
        }
    }

    private void searchBooksByTitle() {
        System.out.print("Enter part of the title: ");
        String search = scanner.nextLine().toLowerCase();

        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(search)) {
                foundBooks.add(book);
            }
        }

        if (foundBooks.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("\n Search Results ");
            for (Book book : foundBooks) {
                System.out.println(book.toString());
            }
        }
    }

    private void borrowBook() {
        System.out.print("Enter ID of the book: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input");
            scanner.nextLine();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        Book bookToBorrow = findBookById(id);

        if (bookToBorrow == null) {
            System.out.println("Error: Book with ID " + id + " not found");
        } else if (bookToBorrow.isAvailable()) {
            bookToBorrow.markAsBorrowed();
            System.out.println("Successfully borrowed: " + bookToBorrow.getTitle());
        } else {
            System.out.println("The book '" + bookToBorrow.getTitle() + "' is already borrowed.");
        }
    }

    private void returnBook() {
        System.out.print("Enter ID of the book to return: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input");
            scanner.nextLine();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        Book bookToReturn = findBookById(id);

        if (bookToReturn == null) {
            System.out.println("Book with ID " + id + " not found.");
        } else if (!bookToReturn.isAvailable()) {
            bookToReturn.markAsReturned();
            System.out.println("returned: " + bookToReturn.getTitle());
        } else {
            System.out.println("The book '" + bookToReturn.getTitle() + "' was not borrowed.");
        }
    }

    private void deleteBookById() {
        System.out.print("Enter ID to delete: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input.");
            scanner.nextLine();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId() == id) {
                iterator.remove();
                System.out.println("Book ID " + id + " ('" + book.getTitle() + "') deleted.");
                return;
            }
        }
        System.out.println("Book with ID " + id + " not founded.");
    }

    private Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}