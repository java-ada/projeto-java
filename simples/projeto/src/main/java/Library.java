import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Library {
    public PhysicalBook addPhysicalBook() {
        System.out.println("Título: ");
        String title = scanner.nextLine();
        System.out.println("ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.println("Descrição: ");
        String description = scanner.nextLine();
        System.out.println("Páginas: ");
        int pages = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Editora: ");
        String publisher = scanner.nextLine();
        System.out.println("Nome do Autor: ");
        String authorName = scanner.nextLine();
        System.out.println("Email do Autor: ");
        String authorEmail = scanner.nextLine();
        Author author = new Author(authorName, authorEmail);
        System.out.println("Em quantas das categorias abaixo ele se encaixa?");
        System.out.println(categorias);
        int numberOfGenres = scanner.nextInt();
        scanner.nextLine();
        Genre[] genres = new Genre[numberOfGenres];
        for (int i = 0; i < numberOfGenres; i++) {
            System.out.println(
                "Qual a categoria ele se encaixa? (Digite apenas 1 por vez): "
            );
            int categoria = scanner.nextInt();
            switch (categoria) {
                case 1:
                    genres[i] = new Genre("Romance", "lol");
                case 2:
                    genres[i] = new Genre("Suspense", "lmao");
                case 3:
                    genres[i] = new Genre("Fantasia", "yippie");
                case 4:
                    genres[i] = new Genre("Ficção Científica", "wow");
            }
            System.out.println(genres[i].name());
        }

        PhysicalBook book = new PhysicalBook(
            title,
            ISBN,
            description,
            pages,
            publisher,
            author,
            genres
        );

        return book;
    }

    public PhysicalBook addPhysicalBook() {
        System.out.println("Título: ");
        String title = scanner.nextLine();
        System.out.println("ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.println("Descrição: ");
        String description = scanner.nextLine();
        System.out.println("Páginas: ");
        int pages = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Editora: ");
        String publisher = scanner.nextLine();
        System.out.println("Nome do Autor: ");
        String authorName = scanner.nextLine();
        System.out.println("Email do Autor: ");
        String authorEmail = scanner.nextLine();
        Author author = new Author(authorName, authorEmail);
        System.out.println("Em quantas das categorias abaixo ele se encaixa?");
        System.out.println(categorias);
        int numberOfGenres = scanner.nextInt();
        scanner.nextLine();
        Genre[] genres = new Genre[numberOfGenres];
        for (int i = 0; i < numberOfGenres; i++) {
            System.out.println(
                "Qual a categoria ele se encaixa? (Digite apenas 1 por vez): "
            );
            int categoria = scanner.nextInt();
            switch (categoria) {
                case 1:
                    genres[i] = new Genre("Romance", "lol");
                case 2:
                    genres[i] = new Genre("Suspense", "lmao");
                case 3:
                    genres[i] = new Genre("Fantasia", "yippie");
                case 4:
                    genres[i] = new Genre("Ficção Científica", "wow");
            }
            System.out.println(genres[i].name());
        }

        PhysicalBook book = new PhysicalBook(
            title,
            ISBN,
            description,
            pages,
            publisher,
            author,
            genres
        );

        return book;
    }
}
