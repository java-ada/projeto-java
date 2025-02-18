package org.grupo3.library.entities;

public final class eBook extends Book {

    private final String fileFormat;
    private final String vendor;

    public eBook(String ISBN13, String title, String description, int pages, String publisher, Author author, Genre[] genres, String fileFormat, String vendor) {
        super(ISBN13, title, description, pages, publisher, author, genres);
        this.fileFormat = fileFormat;
        this.vendor = vendor;
    }


    public String getFileFormat() { return this.fileFormat; }
    public String getVendor() { return this.vendor; }

    @Override
    public String toString() {
        String formattedString = super.toString();
        return formattedString + String.format("Formato de Arquivo: %s%nPlataforma onde Adquiriu: %s%n", fileFormat, vendor);
    }
}
