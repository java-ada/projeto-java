package org.grupo3.library.entities;

import java.time.LocalDate;
import java.util.List;

public class Author {
    private String name;
    private String email;
    private LocalDate dob;  //data de nascimento
    private List<Book> books;

    public Author(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }



    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public LocalDate getDob() {return dob; }

    public void setDob(LocalDate dob) {
        if (dob.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data inválida: Data inserida está no futuro");
        } else {
            this.dob = dob;
        }
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void printAllBooks() {
        System.out.println(this.getName() + " é autor dos seguintes livros: ");
        for (Book book : books) {
            System.out.println(book.getTitle() + ", " + book.getPages() + " Páginas");
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Nome: " + getName();
    }
}
