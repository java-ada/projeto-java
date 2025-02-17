package org.grupo3.library.entities;

import org.grupo3.library.interfaces.Classifiable;

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

    public String getGenreNames() {
        String[] genreArray = this.getGenres().toString().split(",");
        StringBuilder genreNames = new StringBuilder();
        for (int i = 0; i < genreArray.length; i++) {
            if (i % 2 == 0) {
                continue;
            }
            genreNames.append(genreArray[i]).append(";");
        }
        return genreNames.toString();
    }

    public String getGenreDescriptions() {
        String[] genreArray = this.getGenres().toString().split(",");
        StringBuilder genreNames = new StringBuilder();
        for (int i = 0; i < genreArray.length; i++) {
            if (i % 2 != 0) {
                continue;
            }
            genreNames.append(genreArray[i]).append(";");
        }
        return genreNames.toString();
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
        return String.format("ISBN13: %s%nTítulo: %s%nDescrição: %s%nAutor(a): %s%nEditora: %s%nGêneros: %s%nNúmero de Páginas: %d%n", getISBN13(), getTitle(), getDescription(), getAuthor(), getPublisher(), getGenreNames(), getPages());
    }

    private boolean validateISBN13(String isbn13) {
        isbn13 = isbn13.replaceAll("\\-", "");
        isbn13 = isbn13.trim();

        if (!isbn13.matches("^[0-9]*$")) {
            return false;
        }

        if (isbn13.length() != 13) {
            return false;
        }

        int sum = 0;
        char[] tmp = isbn13.toCharArray();

        for (int i = 0; i < 12; i++) {
            if (i % 2 == 0) {
                sum += Character.getNumericValue(tmp[i]);
            } else {
                sum += Character.getNumericValue(tmp[i]) * 3;
            }
        }

        return (10 - (sum % 10)) == Character.getNumericValue(tmp[12]);
    }
}