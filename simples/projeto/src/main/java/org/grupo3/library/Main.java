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

    public static void main(String[] args) {
        var main = new Main();
        var library = new Library();
        try (var scanner = new Scanner(System.in)) {
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
                    String ISBN = scanner.nextLine();
                    if (library.lendBook(ISBN)) System.out.printf("Okay, aqui está o livro %s%n", library.getTitleFromISBN(ISBN));
                    else System.out.println(Arrays.toString(library.getBookFromISBN(ISBN)));
                    break;
                case "3":
                    System.out.println("Insira o ISBN do livro que deseja deletar:");
                    String isbn = scanner.nextLine();
                    library.deleteBookFromLibrary(isbn);
                    break;
                case "4":
                    System.out.println(library.getBooklist());
                    break;
                case "5":
                case "7":
                    break;
                case "6":
                System.out.println("Obrigado e boa leitura. Até a próxima!");
                break;
                }
            } catch (NameNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}