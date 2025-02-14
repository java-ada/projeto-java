public final class eBook extends Book {

    private String fileFormat;
    private String link;
    private double price;

    public eBook(String ISBN13, String title, String description, int pages, String publisher, Author author, Genre[] genres, String fileFormat, String link, double price) {
        super(ISBN13, title, description, pages, publisher, author, genres);
        this.fileFormat = fileFormat;
        this.link = link;
        if (price < 0.0) {
            throw new IllegalArgumentException("Preço inválido: valor negativo");
        } else {
            this.price = price;
        }
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0.0) {
            throw new IllegalArgumentException("Preço inválido: valor negativo");
        } else {
            this.price = price;
        }
    }
}
