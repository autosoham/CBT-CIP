import java.util.ArrayList;
import java.util.List;

public class LibraryCatalog {
    private List<Book> books;

    public LibraryCatalog() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("Books in the catalog:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found: " + book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found with the title: " + title);
        }
    }

    public void searchByAuthor(String author) {
        boolean found = false;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                System.out.println("Found: " + book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found by the author: " + author);
        }
    }
}
