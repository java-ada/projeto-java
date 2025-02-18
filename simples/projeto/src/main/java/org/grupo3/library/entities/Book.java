package org.grupo3.library.entities;

import java.security.InvalidParameterException;

public sealed class Book permits PhysicalBook, eBook {

    private final String ISBN13;
    private final String title;
    private final String description;
    private final int pages;
    private final String publisher;
    private final Author author;
    private final Genre[] genres;

    public Book(String ISBN13, String title, String description, int pages, String publisher, Author author, Genre[] genres) {
        if (!validateISBN13(ISBN13)) {
            throw new InvalidParameterException("ISBN13 Inválido");
        }

        this.ISBN13 = ISBN13;
        this.title = title;
        this.description = description;
        this.pages = pages;
        this.publisher = publisher;
        this.author = author;
        this.genres = genres;
    }

    public String getISBN13() { return ISBN13; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getPages() { return pages; }
    public String getPublisher() { return publisher; }
    public Author getAuthor() { return author; }
    public Genre[] getGenres() { return genres; }

    public String getGenreNames() {
        StringBuilder genreNames = new StringBuilder();
        for (int i = 0; i < genres.length; i++) {
            genreNames.append(genres[i].getName()).append(",");
        }
        return genreNames.toString();
    }

    @Override
    public String toString() {
        return String.format("ISBN13: %s%nTítulo: %s%nDescrição: %s%nAutor(a): %s%nEditora: %s%nGêneros: %s%nNúmero de Páginas: %d%n", ISBN13, title, description, author, publisher, getGenreNames(), pages);
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