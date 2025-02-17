import java.time.LocalDate;
import java.util.List;

public class Author {
    private String name;
    private String email;
    private List<Book> books;

    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }


    public void addBook(Book book) {
        books.add(book);
    }

    public void printAllBooks() {
        System.out.println(this.getName() + " é autor dos seguintes livros: ");
        for (Book book : books) {
            System.out.println(book.getTitle() + ", " + book.getPages() + " Páginas");
        }
    }

    public List<Book> getBooks() {
        return books;
    }
}
