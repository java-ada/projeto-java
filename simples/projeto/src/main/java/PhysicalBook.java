public final class PhysicalBook extends Book implements Lendable {
  private double[] dimensions;
  private boolean available;

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
