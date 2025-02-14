public final class PhysicalBook extends Book{
    private boolean available;

  public PhysicalBook(String ISBN13, String title, String description, int pages, String publisher, Author author, Genre[] genres, boolean available) {
        super(ISBN13, title, description, pages, publisher, author, genres);
        this.available = available;
    }
  public void PhysicalBook(String isbn13, String title, String description, String publisher, Genre[] genres, Author authors, int pages, double[] dimensions) {
    super(isbn13, title, description, publisher, genres, authors, pages);
    this.dimensions = dimensions;
    this.available = true;
  }

  public double[] getDimensions() {
    return dimensions;
  }

  public void lendBook() {
    if (available) {
      available = false;
    } else {
      throw new Exception("Book unavailable");
    }
  }

  public void returnBook() {
    available = true;
  }

  public boolean checkAvailability() {
    return available;
  }
}
