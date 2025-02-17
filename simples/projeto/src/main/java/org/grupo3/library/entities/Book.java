package org.grupo3.library.entities;

import org.grupo3.library.interfaces.Classifiable;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public sealed class Book implements Classifiable permits PhysicalBook, eBook {

    private final String ISBN13;

    private final String title;
    private final String description;
    private final int pages;
    private final String publisher;
    private final Author author;
    private final List<Genre> genres;
    private final double price;


    public Book(
            String ISBN13,
            String title,
            String description,
            int pages,
            String publisher,
            Author author,
            List<Genre> genres,
            double price
    ) {
        this.ISBN13 = ISBN13;
        this.title = title;
        this.description = description;
        this.pages = pages;
        this.publisher = publisher;
        this.author = author;
        this.genres = genres;
        this.price = price;
    }

    public String getISBN13() {
        return ISBN13;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPages() {
        return pages;
    }

    public double getPrice() {
        return price;
    }

    public String getPublisher() {
        return publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public List<Genre> getGenres() {
        return this.genres;
    }

    @Override
    public void addItemToLibrary() throws IOException {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(getISBN13(), book.getISBN13());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getISBN13());
    }

    @Override
    public String toString() {
        return String.format("ISBN13: %s%nTítulo: %s%nDescrição: %s%nAutor(a): %s%nEditora: %s%nGêneros: %s%nNúmero de Páginas: %d%n", getISBN13(), getTitle(), getDescription(), getAuthor(), getPublisher(), getGenres().toString(), getPages());
    }
}