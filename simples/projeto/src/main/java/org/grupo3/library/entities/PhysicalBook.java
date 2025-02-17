package org.grupo3.library.entities;

import org.grupo3.library.interfaces.Classifiable;
import org.grupo3.library.interfaces.Lendable;

import java.io.IOException;

public final class PhysicalBook extends Book implements Lendable, Classifiable {
    private boolean available;
    private final Library library = new Library();

    public PhysicalBook(Book book) {
        super(book.getISBN13(), book.getTitle(), book.getDescription(), book.getPages(), book.getPublisher(), book.getAuthor(), book.getGenres(), book.getPrice());
        this.available = true;
    }

    public void lendBook(PhysicalBook book) throws SecurityException {
          try {
              if (book.isAvailable()) {
                  System.out.println("Okay, aqui está seu livro, boa leitura!");
                  available = false;
              }
              throw new IOException();
          } catch (IOException e) {
              System.out.println("Infelizmente, o livro não está disponível :(");
          }
      }

      public void returnBook(PhysicalBook book) throws IOException {
          if (!available) {
              available = true;
              library.updateLog("physical", "return", book.getTitle());
          } else {
              throw new IllegalCallerException("Livro ainda não foi emprestado");
          }
      }

      public boolean isAvailable() {
          return available;
      }

    @Override
    public String toString() {
        String formattedString = super.toString();
        return formattedString + "Disponível: " + isAvailable();
    }

    @Override
    public void addItemToLibrary() throws IOException {
        library.addBookToLibrary(this);
    }
}
