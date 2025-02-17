package org.grupo3.library;

import org.grupo3.library.entities.Book;
import org.grupo3.library.entities.Library;

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
            System.out.println("Olá, bem-vindo à Caixoteca, como poderia me referir à Vossa Senhoria?");
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
//                    System.out.println("Insira o ISBN do livro que deseja pegar emprestado:");
//                    String lendISBN = scanner.nextLine();
//                    library.lendBook(bookIsbn);
//                    break;
                case "3":
//                    System.out.println("Insira o ISBN do livro que deseja devolver:");
//                    String borrowISBN = scanner.nextLine();
//                    library.returnBook(bookIsbn);
//                    break;
                    break;
                case "4":
                    System.out.println("Insira o ISBN do livro que deseja deletar:");
                    String isbn = scanner.nextLine();
                    library.deleteBookFromLibrary(isbn);
                    break;
                case "5":
                    System.out.println(library.getBooklist());
                    break;
                case "6":
                System.out.println("Obrigado e boa leitura. Até a próxima!");
                break;
                }
            }
    }
}