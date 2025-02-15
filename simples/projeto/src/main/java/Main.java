import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
        throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        var library = new Library();

        String categorias =
            """
                1- Romance       2- Suspense
                3- Fantasia      4- Ficção Científica
            """;

        Genre[] genre1 = {
            new Genre("Fantasia", "lol"),
            new Genre("Drama", "omg"),
        };
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
            System.out.println(
                "Olá, bem-vindo à Caixoteca. Por favor, qual o seu nome?"
            );
            String user = scanner.nextLine();
            System.out.printf("Olá, %s. O que deseja?\n", user);
            System.out.println(menu);
            System.out.print("Option: ");
            String option = scanner.nextLine();
            System.out.printf("A opção %s foi escolhida.\n", option);

            switch (option) {
                case "1":
                    library.addBook();
                    System.out.println(
                        "Livro adicionado! Voltando ao menu principal..."
                    );
                    break;
                case "5":
                    System.out.println("Encontramos os seguintes livros: ");
                    books
                        .stream()
                        .map(Book::getTitle)
                        .forEach(System.out::println);
                    break;
                case "6":
                    System.out.println(
                        "Obrigado e boa leitura. Até a próxima!"
                    );
                    loggedIn = false;
                    break;
                case "7":
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

        Genre[] genre1 = {
            new Genre("Fantasy", "lmao"),
            new Genre("Drama", "lol"),
        };
        Genre[] genre2 = { new Genre("Adventure", "poggers") };
        Genre[] genre3 = { new Genre("Epic", "sadge") };

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
