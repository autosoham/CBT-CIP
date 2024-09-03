import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nLibrary Catalog System");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Search by Title");
            System.out.println("4. Search by Author");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    catalog.addBook(new Book(title, author));
                    break;
                case 2:
                    catalog.listBooks();
                    break;
                case 3:
                    System.out.print("Enter title to search: ");
                    String searchTitle = scanner.nextLine();
                    catalog.searchByTitle(searchTitle);
                    break;
                case 4:
                    System.out.print("Enter author to search: ");
                    String searchAuthor = scanner.nextLine();
                    catalog.searchByAuthor(searchAuthor);
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
