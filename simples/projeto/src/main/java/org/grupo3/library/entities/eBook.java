package org.grupo3.library.entities;

import java.io.IOException;

public final class eBook extends Book {

    private final String fileFormat;
    private final String vendor;
    private final Library library = new Library();

    @Override
    public void addItemToLibrary() throws IOException {
        library.addBookToLibrary(this);
    }

    public eBook(Book book, String fileFormat, String vendor) {
        super(book.getISBN13(), book.getTitle(), book.getDescription(), book.getPages(), book.getPublisher(), book.getAuthor(), book.getGenres(), book.getPrice() );
        this.fileFormat = fileFormat;
        this.vendor = vendor;
    }


    public String getFileFormat() {
        return this.fileFormat;
    }

    public String getVendor() {
        return this.vendor;
    }


    @Override
    public String toString() {
        String formattedString = super.toString();
        return formattedString + "Formato de Arquivo: %s%nPlataforma onde Adquiriu: %s%n";
    }
}
