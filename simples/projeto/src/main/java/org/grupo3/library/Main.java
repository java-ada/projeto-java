package org.grupo3.library;

import org.grupo3.library.entities.Book;
import org.grupo3.library.entities.Library;

import javax.naming.NameNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private String user = "user";

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return this.user;
    }

    public static void main(String[] args) throws NameNotFoundException, IOException {
        var main = new Main();
        var library = new Library();
        try (var scanner = new Scanner(System.in)) {
            String isbn = "";
            System.out.println("Olá, bem-vindo à Caixoteca, como poderia me referir à você?");
            String user = scanner.nextLine();
            main.setUser(user);

            System.out.printf("Olá, %s. O que desejas?\n", user);
            library.showMenu();
            System.out.print("Option: ");
            String option = scanner.nextLine();
            System.out.printf("A opção %s foi escolhida.\n", option);

            switch (option) {
                case "1":
                    Book addedBook = library.addBookInfo();
                    System.out.printf("Livro Adicionado Completamente!%nDetalhes Finais do Livro:%n%s", addedBook);
                    break;
                case "2":
                    System.out.println("Por favor, insira o ISBN do livro que deseja pegar emprestado:");
                    isbn = scanner.nextLine();
                    if (library.lendBook(isbn)) System.out.printf("Okay, aqui está o livro %s%n", library.getTitleFromISBN(isbn));
                    else System.out.printf(Arrays.toString(library.getBookFromISBN(isbn)));
                    break;
                case "3":
                    System.out.println("Insira o ISBN do livro que deseja devolver:");
                    isbn = scanner.nextLine();
                    if (library.returnBook(isbn)) System.out.printf("Okay, livro %s devolvido com sucesso%n", library.getTitleFromISBN(isbn));
                    else System.out.println("Não foi possível localizar nenhum livro com ISBN = " + isbn);
                    break;
                case "4":
                    System.out.println("Insira o ISBN do livro que deseja deletar:");
                    isbn = scanner.nextLine();
                    library.deleteBookFromLibrary(isbn);
                    break;
                case "5":
                    System.out.println(library.getBooklist());
                case "6":
                System.out.println("Obrigado e boa leitura. Até a próxima!");
                break;
                }
            } catch (NameNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}