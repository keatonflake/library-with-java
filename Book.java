public class Book{
    private String title;
    private String author;
    private String isbn;
// constructor- requires title, author, isbn to create instance of book class
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
// method to get book title
    public String getTitle(){
        return title;
    }
// method to get book author
    public String getAuthor() {
        return author;
    }
//  method to get ISBN :)
    public String getIsbn() {
        return isbn;
    }
    // this override method will run instead of the method in it's superclass
    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn;
    }
}