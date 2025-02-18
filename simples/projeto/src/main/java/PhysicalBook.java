import org.grupo3.library.interfaces.Lendable;

public final class PhysicalBook extends Book implements Lendable {
    private boolean available;

    public PhysicalBook(String ISBN13, String title, String description, int pages, String publisher, Author author, Genre[] genres) {
        super(ISBN13, title, description, pages, publisher, author, genres);
        available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void lendBook(){
        if (!available) {
            throw new IllegalArgumentException("Livro indisponível");
        }
        available = false;
    }

    public void returnBook() {
        if (available) {
            throw new IllegalArgumentException("Este livro não foi emprestado");
        }
        available = true;
    }


    @Override
    public String toString() {
        String formattedString = super.toString();
        return formattedString + "Disponível: " + available;
    }
}
