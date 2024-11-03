import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

public class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }

    public void removeBook(String title) {
        boolean removed = books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        if (removed) {
            System.out.println("Book removed: " + title);
        } else {
            System.out.println("Book not found: " + title);
        }
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Books in the Library:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void writeBooksToJson(String filename) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write("[\n");
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                fileWriter.write("  {\n");
                fileWriter.write("    \"title\": \"" + book.getTitle() + "\",\n");
                fileWriter.write("    \"author\": \"" + book.getAuthor() + "\",\n");
                fileWriter.write("    \"isbn\": \"" + book.getIsbn() + "\"\n");
                fileWriter.write("  }");
                if (i < books.size() - 1) {
                    fileWriter.write(",\n");
                } else {
                    fileWriter.write("\n");
                }
            }
            fileWriter.write("]");
            System.out.println("Books written to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public void readBooksFromJson(String filename) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            String json = jsonBuilder.toString();
            json = json.substring(1, json.length() - 1);

            String[] bookEntries = json.split("},\\s*\\{");
            for (String entry : bookEntries) {
                entry = entry.replaceAll("[{}]", "");
                String[] properties = entry.split(",\\s*");
                String title = "", author = "", isbn = "";

                for (String property : properties) {
                    String[] keyValue = property.split(":\\s*");
                    if (keyValue[0].contains("title")) {
                        title = keyValue[1].replace("\"", "").trim();
                    } else if (keyValue[0].contains("author")) {
                        author = keyValue[1].replace("\"", "").trim();
                    } else if (keyValue[0].contains("isbn")) {
                        isbn = keyValue[1].replace("\"", "").trim();
                    }
                }

                addBook(new Book(title, author, isbn));
            }

            System.out.println("Books added from " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
