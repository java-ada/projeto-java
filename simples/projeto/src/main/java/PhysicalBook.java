import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class PhysicalBook extends Book implements Lendable, Logger {

    private boolean available;
    private double[] dimensions;

    public PhysicalBook(String ISBN13, String title, String description, int pages, String publisher, Author author, Genre[] genres, boolean available) {
        super(ISBN13, title, description, pages, publisher, author, genres);
        this.available = available;
    }
  public PhysicalBook(
        String ISBN13,
        String title,
        String description,
        int pages,
        String publisher,
        Author author,
        Genre[] genres,
        boolean available,
        double[] dimensions
    ) {
        super(ISBN13, title, description, pages, publisher, author, genres);
        this.available = available;
        this.dimensions = dimensions;
    }

    public double[] getDimensions() {
        return dimensions;
    }

    public void lendBook() {
        if (available) {
            available = false;
            this.updateLog("lent");
        } else {
            throw new IllegalCallerException("Livro indisponível");
        }
    }

    public void returnBook() {
        if (!available) {
            available = true;
            this.updateLog("returned");
        } else {
            throw new IllegalCallerException("Livro ainda não foi emprestado");
        }
    }

    public boolean checkAvailability() {
        return available;
    }

    public void updateLog(String operator) {
        String logPath =
            System.getProperty("user.dir") + "\\Logs\\LentBooksLog.txt";
        File log = new File(logPath);
        String line = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss"));
        switch (operator) {
            case "lent" -> line += " - Livro emprestado: ";
            case "returned" -> line += " - Livro devolvido: ";
            default -> throw new IllegalCallerException(
                "Operador inválido: Operador de atualização precisa ser \"lent\" (emprestado) ou \"returned\" (devolvido)"
            );
        }
        line += super.getTitle();

        try (FileWriter logWriter = new FileWriter(log, true)) {
            log.getParentFile().mkdir();
            log.createNewFile();
            logWriter.write(line + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
