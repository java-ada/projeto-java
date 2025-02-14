import java.util.Arrays;

import java.util.stream.Collectors;



public abstract sealed class Book permits PhysicalBook, eBook {

    private final String ISBN13;
    private final String title;
    private final String description;
    private final int pages;
    private final String publisher;
    private final Author author;
    private final Genre[] genres;


    public Book(String ISBN13, String title, String description, int pages, String publisher, Author author, Genre[] genres) {

        this.ISBN13 = ISBN13;
        this.title = title;
        this.description = description;
        this.pages = pages;
        this.publisher = publisher;
        this.author = author;
        this.genres = genres;

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


    public String getPublisher() {

        return publisher;

    }


    public Author getAuthor() {

    }


    public Genre[] getGenres() {

        return genres;

    }


    public void details() {

        String bookAuthor = this.author.getName();

        var categorias = Arrays.stream(this.genres).map(Genre::name).collect(Collectors.toSet());

        var json =

                """

                    ISBN13: %s,
                    Título: %s,
                    Descrição: %s,
                    Páginas: %d,
                    Editora: %s,
                    Autor: %s,
                    Categorias: %s,

                """;

        System.out.println(json.formatted(this.ISBN13, this.title, this.description, this.pages, this.publisher, bookAuthor, categorias));

    }

}