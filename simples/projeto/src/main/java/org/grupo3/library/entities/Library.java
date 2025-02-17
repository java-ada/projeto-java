package org.grupo3.library.entities;

import org.grupo3.library.Main;
import org.grupo3.library.interfaces.Logger;

import javax.naming.NameNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class Library implements Logger {
    private static final Path WORKDIR_PATH = Path.of(System.getProperty("user.dir"));
    private static final Path FILE_PATH = Path.of(WORKDIR_PATH.toString(), "books.csv");
    private static final String STRING_FORMAT = "%s,%s,%s,%d,%s,%s,%s,%s,%s,%s%.2f%n";

    @Override
    public FileHandler createLogFile(String type) throws IOException, SecurityException {
        final Path LOG_PATH = Path.of(WORKDIR_PATH.toString(), "logs/");
        if (Files.notExists(LOG_PATH) ) {
            Files.createDirectories(LOG_PATH);
        }
        if (Objects.equals(type, "physical") ) {
            String LOG_FILE_PATH = Path.of(LOG_PATH.toString(), "physicalBooks.log").toString();
            return new FileHandler(LOG_FILE_PATH, true);
        } else if (Objects.equals(type, "eBook") ) {
            String LOG_FILE_PATH = Path.of(LOG_PATH.toString(), "eBooks.log").toString();
            return new FileHandler(LOG_FILE_PATH, true);
        }
        String LOG_FILE_PATH = Path.of(LOG_PATH.toString(), "global.log").toString();
        return new FileHandler(LOG_FILE_PATH, true);
    }

    @Override
    public void updateLog(String type, String operator, String title) throws IOException, SecurityException {
        var logWriter = createLogFile(type);
        LogRecord logRecord = new LogRecord(Level.INFO, this.logLineBuilder(type, operator, title));
        logWriter.publish(logRecord);
    }

    @Override
    public String logLineBuilder(String type, String operator, String title) {
        var mainInstance = new Main();
        LocalDateTime now = LocalDateTime.now();
        String operation = type + " " + operator;
        return switch (operation) {
            case "ebook " -> String.format("[%tF]: eBook Adicionado: %s por %s%n", now, title, mainInstance.getUser());
            case "physical lent" -> String.format("[%tF]: Livro Físico Emprestado: %s para %s%n", now, title, mainInstance.getUser());
            case "physical return" -> String.format("[%tF]: Livro Físico Devolvido: %s por %s%n", now, title, mainInstance.getUser());
            default -> String.format("[%s]: Livro Físico Adicionado: %s por %s%n", now, title, mainInstance.getUser());
        };
    }

    public void showMenu() {
        String menu =
                """
                        1 - Adicionar um livro           2 - Pegar um livro emprestado    3 - Devolver um livro
                        4 - Deletar um livro             5 - Listar todos os livros       5 - Sair
                """;
        System.out.println(menu);
    }

    public Book addBookInfo() {
        String categorias =
                """
                    Romance       Suspense
                    Fantasia      Ficção Científica
                """;

        System.out.println("ISBN: ");
        try (var scanner = new Scanner(System.in)) {
            String ISBN = scanner.nextLine();
            System.out.println("Título: ");
            String title = scanner.nextLine();
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
            var author = new Author(authorName, authorEmail);
            System.out.printf("Quanto custa o livro %s?%n", title);
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Em quantas das categorias abaixo ele se encaixa?");
            System.out.println(categorias);
            int numberOfGenres = Integer.parseInt(scanner.nextLine() );
            Genre[] genres = new Genre[numberOfGenres];
            for (int i = 0; i < numberOfGenres; i++) {
                System.out.println(
                        "Qual a categoria onde ele se encaixa? (Digite apenas 1 por vez): "
                );
                int categoria = Integer.parseInt(scanner.nextLine());
                switch (categoria) {
                    case 1:
                        genres[i] = new Genre("Romance", "lol");
                        break;
                    case 2:
                        genres[i] = new Genre("Suspense", "lmao");
                        break;
                    case 3:
                        genres[i] = new Genre("Fantasia", "yippie");
                        break;
                    case 4:
                        genres[i] = new Genre("Ficção Científica", "wow");
                        break;
                }
            }
            List<Genre> genreList = Arrays.stream(genres).toList();
            Book bookInfo = new Book(ISBN, title, description, pages, publisher, author, genreList, price);
            System.out.printf("Detalhes parciais do livro adicionados! %nDetalhes Parciais do Livro: %n%s%n%nO livro adicionado se trata de um eBook? [s/N]", bookInfo);

            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("s")) {
                this.updateLog("eBook", "", bookInfo.getTitle());
                return this.addEBook(bookInfo);
            }
            this.updateLog("physical", "", bookInfo.getTitle());
            return this.addPhysicalBook(bookInfo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Book addEBook(Book bookInfo) {
        final String FILE_FORMAT_MSG = """
                    1 - .PDF   2 - .EPUB   3 - .MOBI    4 - .AZW    5 - Não Listado ou Não Sei :(
                """;
        System.out.println("Okay, só precisamos de mais algumas informações: ");
        System.out.println("Por favor, nos informe o formato de arquivo:");
        System.out.println(FILE_FORMAT_MSG);

        try (var scanner = new Scanner(System.in) ) {
            int fileFormatChoice = Integer.parseInt(scanner.nextLine());
            String fileFormat = switch (fileFormatChoice) {
                case 1 -> "Arquivo PDF";
                case 2 -> "Arquivo EPUB";
                case 3 -> "Arquivo MOBI";
                case 4 -> "Arquivo AZW";
                default -> "Desconhecido";
            };
            System.out.println("Okay, então o Arquivo está no formato: " + fileFormat);
            System.out.println("Poderia nos dizer a plataforma onde possui o eBook?");
            String vendor = scanner.nextLine();
            System.out.println("Okay, você possui o eBook na plataforma: " + vendor);

            Book book = new eBook(bookInfo, fileFormat, vendor);
            try {
                this.addBookToLibrary(book);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return book;
        }
    }

    public Book addPhysicalBook(Book bookInfo) {
        Book book = new PhysicalBook(bookInfo);
        try {
            this.addBookToLibrary(book);
            this.updateLog("physical", "", book.getTitle());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    public boolean createOrOpenLibraryFile() throws IOException {
        if (!Files.exists(FILE_PATH) ) {
            Files.createDirectories(WORKDIR_PATH);
            Files.createFile(FILE_PATH);
            try (FileWriter writer = new FileWriter(FILE_PATH.toString())) {
                writer.write(String.format("isbn,titulo,descricao,paginas,editora,autor,generos,isDigital,fileFormat,vendor,price%n"));
                return true;
            } catch (IOException e) {
                System.out.println("Não foi possível abrir o arquivo, verifique se você possui permissões para acessar o caminho informado!");
                return false;
            }
        } else {
            return true;
        }
    }

    public void addBookToLibrary(Book book) throws IOException {
        if (createOrOpenLibraryFile()) {
            try (FileWriter writer = new FileWriter(FILE_PATH.toString(), true)) {
                String line = String.format(STRING_FORMAT,
                        book.getISBN13(),
                        book.getTitle(),
                        book.getDescription(),
                        book.getPages(),
                        book.getPublisher(),
                        book.getAuthor(),
                        book.getGenres().toString(),
                        "false",
                        "N/A",
                        "N/A",
                        book.getPrice()
                );
                writer.write(line);
            } catch (IOException e) {
                System.out.println("Não foi possível abrir o arquivo, verifique se você possui permissões para acessar o caminho informado!");
                throw e;
            }
        }
    }

    public void addBookToLibrary(eBook book) throws IOException {
        String line = String.format(STRING_FORMAT,
                book.getISBN13(),
                book.getTitle(),
                book.getDescription(),
                book.getPages(),
                book.getPublisher(),
                book.getAuthor(),
                book.getGenres().toString(),
                "true",
                book.getFileFormat(),
                book.getVendor(),
                book.getPrice()
        );

        if (createOrOpenLibraryFile()) {
            try (FileWriter writer = new FileWriter(FILE_PATH.toString(), true)) {
                this.updateLog("eBook", " ", book.getTitle());
                writer.write(line);
            } catch (IOException e) {
                System.out.println("Não foi possível abrir o arquivo, verifique se você possui permissões para acessar o caminho informado!");
                throw e;
            }
        }
    }

    public void deleteBookFromLibrary(String ISBN) {
        try (FileWriter writer = new FileWriter(FILE_PATH.toString()) ) {
            List<String> result =  Files.readAllLines(FILE_PATH);
            if (result.isEmpty()) {
                throw new NameNotFoundException();
            }
            List<String> filteredResults = result.stream().filter((e) -> e.equalsIgnoreCase(ISBN)).toList();
            if (result.removeAll(filteredResults)) {
                System.out.println(result);
                Files.copy(FILE_PATH, Path.of(WORKDIR_PATH.toString(), "tmp", "backupFile.csv"));
                writer.write(result.stream().toString());
            }
        } catch (NameNotFoundException | IOException notFoundException) {
            System.out.println("Não foi possível encontrar um livro com este ISBN na Biblioteca");
        }
    }

    public void lendBook(String ISBN) {}

    public void returnBook(String ISBN) {}

    public ArrayList<String> getBooklist() {
        var books = new ArrayList<String>();
        try {
            List<String> lines = Files.readAllLines(FILE_PATH);
            for(String line : lines) {
                String[] oneBook = line.split(",");
                books.add(oneBook[1] + " (ISBN: " + oneBook[0] + ")");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return books;
    }
}
