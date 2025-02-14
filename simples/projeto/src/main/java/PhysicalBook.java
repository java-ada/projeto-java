public final class PhysicalBook extends Book{
    private boolean Available;

    public PhysicalBook(String ISBN13, String title, String description, int pages, String publisher, Author author, Genre[] genres, boolean available) {
        super(ISBN13, title, description, pages, publisher, author, genres);
        Available = available;
    }

}
