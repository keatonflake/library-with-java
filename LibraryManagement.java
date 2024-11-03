import java.util.Scanner;

public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the Library Management System!");

        do {
            System.out.println("\nChoose an action:");
            System.out.println("1. Add a Book");
            System.out.println("2. Remove a Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Write Books to JSON");
            System.out.println("5. Read Books from JSON");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(new Book(title, author, isbn));
                    break;

                case "2":
                    System.out.print("Enter the title of the book to remove: ");
                    String bookTitle = scanner.nextLine();
                    library.removeBook(bookTitle);
                    break;

                case "3":
                    library.displayBooks();
                    break;

                case "4":
                    System.out.print("Enter filename to save books (e.g., books.json): ");
                    String writeFilename = scanner.nextLine();
                    library.writeBooksToJson(writeFilename);
                    break;

                case "5":
                    System.out.print("Enter filename to read books from (e.g., books.json): ");
                    String readFilename = scanner.nextLine();
                    library.readBooksFromJson(readFilename);
                    break;

                case "6":
                    System.out.println("Exiting the Library Management System.");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, 4, 5, or 6.");
                    break;
            }
        } while (!command.equals("6"));

        scanner.close();
    }
}
