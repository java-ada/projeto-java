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
    private static final String STRING_FORMAT = "%s,%s,%s,%s,%d,%s,%s,%s,%s,%s,%s,%s%.2f%n";
    private static final Path TMP_PATH = Path.of(WORKDIR_PATH.toString(), "tmp");
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
                        4 - Deletar um livro             5 - Listar todos os livros       6 - Sair
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
        if (Files.notExists(FILE_PATH) ) {
            Files.createDirectories(WORKDIR_PATH);
            Files.createFile(FILE_PATH);
            try (FileWriter writer = new FileWriter(FILE_PATH.toString())) {
                writer.write(String.format("isAvailable,isbn,title,description,pages,publisher,author,genreName,genreDescription,isDigital,fileFormat,vendor,price%n"));
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
                writer.write(csvLineBuilder(book));
            } catch (IOException e) {
                System.out.println("Não foi possível abrir o arquivo, verifique se você possui permissões para acessar o caminho informado!");
                throw e;
            }
        }
    }

    public void addBookToLibrary(eBook book) throws IOException {
        if (createOrOpenLibraryFile()) {
            try (FileWriter writer = new FileWriter(FILE_PATH.toString(), true)) {
                this.updateLog("eBook", " ", book.getTitle());
                writer.write(this.csvLineBuilder(book));
            } catch (IOException e) {
                System.out.println("Não foi possível abrir o arquivo, verifique se você possui permissões para acessar o caminho informado!");
                throw e;
            }
        }
    }

    private String csvLineBuilder(Book book) {
        return String.format(STRING_FORMAT,
                    "true",
                    book.getISBN13(),
                    book.getTitle(),
                    book.getDescription(),
                    book.getPages(),
                    book.getPublisher(),
                    book.getAuthor(),
                    book.getGenreNames(),
                    book.getGenreDescriptions(),
                    "false",
                    "N/A",
                    "N/A",
                    book.getPrice()
            );
    }

    private String csvLineBuilder(eBook book) {
        return String.format(STRING_FORMAT,
                "true",
                book.getISBN13(),
                book.getTitle(),
                book.getDescription(),
                book.getPages(),
                book.getPublisher(),
                book.getAuthor(),
                book.getGenreNames(),
                book.getGenreDescriptions(),
                "true",
                book.getFileFormat(),
                book.getVendor(),
                book.getPrice()
        );
    }

    private String csvLineBuilder(String ISBN) throws NameNotFoundException, IOException {
        String[] bookInfo = getBookFromISBN(ISBN);
        StringBuilder builder = new StringBuilder();

        for (String string : bookInfo) {
            builder.append(string);
        }

        return builder.toString();
    }


    public boolean deleteBookFromLibrary(String ISBN) throws IOException, NameNotFoundException {
        boolean success = false;
        String bookInfo = csvLineBuilder(ISBN);

        Files.createDirectories(TMP_PATH);
        if (Files.notExists(Path.of(TMP_PATH + "/backupFile.csv"))) {
            Files.createFile(Path.of(TMP_PATH + "/backupFile.csv"));
        } else {
            Files.deleteIfExists(Path.of(TMP_PATH + "/backupFile.csv"));
            Files.copy(FILE_PATH, Path.of(TMP_PATH.toString(), "backupFile.csv"));
        }

        try (FileWriter writer = new FileWriter(FILE_PATH.toString()) ) {
            List<String> result =  Files.readAllLines(FILE_PATH);

            if (result.isEmpty()) {
                throw new NameNotFoundException();
            }
            if (result.contains(bookInfo)) {
                Files.deleteIfExists(FILE_PATH);
                if (Files.notExists(FILE_PATH) ){
                    createOrOpenLibraryFile();
                }
                String filtered = result.stream().filter(s -> !s.contains(bookInfo)).toString();
                Files.createTempFile(Path.of(TMP_PATH.toString(), "tmp.csv").toString(), filtered);
                System.out.println(result);
                Files.copy(Path.of(TMP_PATH.toString(), "/tmp.csv"), FILE_PATH);
                success = true;
            }
        } catch (NameNotFoundException | IOException notFoundException) {
            System.out.println("Não foi possível encontrar um livro com este ISBN na Biblioteca");
            success = false;
        }
        return success;
    }

    public List<String> getBooklist() {
        try {
            List<String> strings = new ArrayList<>();
            for (String line : Files.readAllLines(FILE_PATH) ) {
                if (line.startsWith("is")) continue;
                strings.add(line);
            }
            return strings;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean changeAvailability(String ISBN) throws NameNotFoundException, IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH.toString())) {
            String[] bookInfo = getBookFromISBN(ISBN);
            String newState;
            if (isBookAvailable(ISBN)) newState = "false";
            else newState = "true";
            bookInfo[0] = bookInfo[0].replaceAll("^.*", newState);
            if (deleteBookFromLibrary(ISBN)) {
                for (String string : bookInfo) {
                    writer.write(string);
                }
            }
            return Boolean.parseBoolean(newState);
        }
    }

    public boolean lendBook(String ISBN) throws NameNotFoundException, IOException {
        System.out.println("Estado Inicial: " + isBookAvailable(ISBN));
        if (!changeAvailability(ISBN)) {
            updateLog("physical", "lent", getTitleFromISBN(ISBN));
            System.out.println("Novo Estado: " + isBookAvailable(ISBN));
            return true;
        }
        System.out.println("Não foi possível alterar");
        return false;
    }

    public boolean returnBook(String ISBN) throws NameNotFoundException, IOException {
        if (!isBookAvailable(ISBN)) {
            System.out.println("Estado Inicial: " + isBookAvailable(ISBN));
            if (changeAvailability(ISBN)) {
                updateLog("physical", "return", getTitleFromISBN(ISBN));
                System.out.println("Novo Estado: " + isBookAvailable(ISBN));
                return true;
            }
        }
        System.out.println("Não foi possível alterar");
        return false;
    }


    public boolean isBookAvailable(String ISBN) throws IOException, NameNotFoundException {
        String[] bookInfo = getBookFromISBN(ISBN);
        return Boolean.parseBoolean(bookInfo[0]);
    }

    public String[] getBookFromISBN(String ISBN) throws NameNotFoundException, IOException {
        try {
            if (Files.exists(FILE_PATH)) {
                String bookInfo = Files.readAllLines(FILE_PATH).stream().filter(e -> e.contains(ISBN)).findFirst().toString();
                return splitCsvLines(bookInfo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String[]{"Não conseguimos encontrar o livro. Por favor, verifique se inseriu os dados corretamente.", "ISBN: ", ISBN};
    }

    private String[] splitCsvLines(String bookInfo) {
        return bookInfo.split(",");
    }

    public String getTitleFromISBN(String ISBN) throws NameNotFoundException, IOException {
        return getBookFromISBN(ISBN)[2];
    }
}
