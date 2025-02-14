import java.util.Arrays;
import java.util.stream.Collectors;

public abstract sealed class Book permits PhysicalBook {
    private String ISBN13;
    private String title;
    private String description;
    private int pages;
    private String publisher;
    private Author author;
    private Genre[] genres;

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

    public void setISBN13(String ISBN13) {
        this.ISBN13 = ISBN13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public void setGenres(Genre[] genres) {
        this.genres = genres;
    }

    public void details() {
        String bookAuthor = this.author.getName();
        var genreArray = Arrays.toString(this.genres);
        var categorias =  Arrays.stream(this.genres).map(Genre::name).collect(Collectors.toSet());
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


//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("ISBN13: ").append(ISBN13).append(" | ");
//        sb.append("Title: ").append(title).append(" | ");
//        sb.append("Description: ").append(description).append(" | ");
//        sb.append("Pages: ").append(pages).append(" | ");
//        sb.append("Publisher: ").append(publisher).append(" | ");
//        sb.append("Author: ").append(author.toString()).append(" | ");
//        sb.append("Genres: ");
//        for (int i = 0; i < genres.length; i++) {
//            sb.append(genres[i].toString());
//            if (i < genres.length - 1) {
//                sb.append(", ");
//            }
//        }
//        return sb.toString().trim();
//    }

}
