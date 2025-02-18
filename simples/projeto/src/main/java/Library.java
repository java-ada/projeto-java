import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    public void mainMenu() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        String categorias =
                """
                    1- Romance       2- Suspense
                    3- Fantasia      4- Ficção Científica
                """;

        Genre[] genre1 = {new Genre("Fantasia", "lol"), new Genre("Drama", "omg")};
        Author author1 = new Author("J.K. Rowling", "jkrowling@example.com");

        PhysicalBook book1 = new PhysicalBook(
                "978-0747532699",
                "Harry Potter and the Philosopher's Stone",
                "A young wizard's journey begins.",
                223,
                "Bloomsbury",
                author1,
                genre1,
                true
        );

        List<PhysicalBook> books = getPhysicalBookList();

        boolean loggedIn = true;

        while (loggedIn) {

            Thread.sleep(5000);

            String menu =
                    """
  
                            1- Adicionar um livro físico    2- Adicionar um livro digital   3- Pegar um livro emprestado 
                            4- Deletar um livro             5- Listar todos os livros       6- Sair
                    """;
            System.out.println("Olá, bem-vindo à Caixoteca. Por favor, qual o seu nome?");
            String user = scanner.nextLine();
            System.out.printf("Olá, %s. O que deseja?\n", user);
            System.out.println(menu);
            System.out.print("Option: ");
            String option = scanner.nextLine();
            System.out.printf("A opção %s foi escolhida.\n", option);

            switch (option) {
                case "1":
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
                    System.out.println("Em quantas das cantegorias abaixo ele se encaixa?");
                    System.out.println(categorias);
                    int numberOfGenres = scanner.nextInt();
                    scanner.nextLine();
                    Genre[] genres = new Genre[numberOfGenres];
                    for (int i = 0; i < numberOfGenres; i++) {
                        System.out.println("Qual a categoria ele se encaixa? (Digite apenas 1 por vez): ");
                        int categoria = scanner.nextInt();
                        switch (categoria) {
                            case 1:
                                genres[i] = new Genre("Romance", "Um livro de Romance");
                            case 2:
                                genres[i] = new Genre("Suspense", "Um livro de Suspense");
                            case 3:
                                genres[i] = new Genre("Fantasia", "Um livro de Fantasia");
                            case 4:
                                genres[i] = new Genre("Ficção Científica", "Um livro de Ficção Científica");
                        }
                        System.out.println(genres[i].name());
                    }
                    System.out.println("O livro é físico ou digital? (Digite 1 para físico e 2 para digital):");
                    int typeOfBook = scanner.nextInt();
                    if (typeOfBook == 1) {
                        books.add(new PhysicalBook(ISBN, title, description, pages, publisher, author, genres, true));
                        System.out.println("Livro adicionado! Voltando ao menu principal...");
                    } else {
                        System.out.println("Preço do livro digital: ");
                        Double bookPrice = scanner.nextDouble();
                        System.out.println("Formato do livro digital: ");
                        String bookFormat = scanner.nextLine();
                        System.out.println("Link do livro digital: ");
                        String bookLink = scanner.nextLine();
                        books.add(new eBook(ISBN, title, description, pages, publisher, author, genres, bookFormat, bookLink, bookPrice));
                    }
                    break;

                case "5":
                    System.out.println("Encontramos os seguintes livros: ");
                    books.stream().map(Book::getTitle).forEach(System.out::println);
                    break;
                case "6":
                    System.out.println("Obrigado e boa leitura. Até a próxima!");
                    loggedIn = false;
                    break;
                case"7":
                    book1.details();
                    break;

            }
        }

        scanner.close();
    }
    private static List<PhysicalBook> getPhysicalBookList() {
        List<PhysicalBook> books = new ArrayList<>();

        Author author1 = new Author("J.K. Rowling", "jkrowling@example.com");
        Author author2 = new Author("George R.R. Martin", "grrm@example.com");
        Author author3 = new Author("J.R.R. Tolkien", "jrrtolkien@example.com");

        Genre[] genre1 = {new Genre("Fantasy", "lmao"), new Genre("Drama", "lol")};
        Genre[] genre2 = {new Genre("Adventure", "poggers")};
        Genre[] genre3 = {new Genre("Epic", "sadge")};

        PhysicalBook book1 = new PhysicalBook(
                "978-0747532699",
                "Harry Potter and the Philosopher's Stone",
                "A young wizard's journey begins.",
                223,
                "Bloomsbury",
                author1,
                genre1,
                true
        );

        PhysicalBook book2 = new PhysicalBook(
                "978-0553103540",
                "A Game of Thrones",
                "The struggle for the Iron Throne.",
                694,
                "Bantam Books",
                author2,
                genre2,
                true
        );

        PhysicalBook book3 = new PhysicalBook(
                "978-0618640157",
                "The Lord of the Rings",
                "An epic quest to destroy the One Ring.",
                1178,
                "George Allen & Unwin",
                author3,
                genre3,
                true
        );

        books.add(book1);
        books.add(book2);
        books.add(book3);
        return books;
    }
}

