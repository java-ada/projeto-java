package org.grupo3.library.interfaces;

import org.grupo3.library.entities.PhysicalBook;

public interface Lendable {
    void lendBook(PhysicalBook book);
    void returnBook(PhysicalBook book);
}
