package org.grupo3.library.interfaces;

import org.grupo3.library.entities.PhysicalBook;

import java.io.IOException;

public interface Lendable {
    void lendBook(PhysicalBook book) throws IOException;
    void returnBook(PhysicalBook book) throws IOException;
}
