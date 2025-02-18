import java.util.ArrayList;

public class Library {
        private static Library instance;
        private ArrayList<PhysicalBook> books = new ArrayList<PhysicalBook>();
        private ArrayList<eBook> eBooks = new ArrayList<eBook>();

        private Library(){}

        public static Library getInstance(){
            if (instance == null) {
                instance = new Library();
            }
            return instance;
        }

        public void addBook(String ISBN13, String title, String description, int pages, String publisher, Author author, Genre[] genres) {
            books.add(new PhysicalBook(ISBN13, title, description, pages, publisher, author, genres));
        }

        public void addEBook(String ISBN13, String title, String description, int pages, String publisher, Author author, Genre[] genres, String fileFormat, String vendor) {
            eBooks.add(new eBook(ISBN13, title, description, pages, publisher, author, genres, fileFormat, vendor));
        }

        public void listBooks() {
            for (Book book : books) {
                System.out.println(book.toString());
            }

            for (Book book : eBooks) {
                System.out.println(book.toString());
            }
        }

        public void removeBook(String ISBN13) {
            for (int i = 0; i < books.size(); i++) {
                if (ISBN13.equals(books.get(i).getISBN13())) {
                    books.remove(i);
                    break;
                }
            }
        }
        
        public void removeEBook(String ISBN13) {
            for (int i = 0; i < eBooks.size(); i++) {
                if (ISBN13.equals(eBooks.get(i).getISBN13())) {
                    eBooks.remove(i);
                    break;
                }
            }
        }

        public void lendBook(String ISBN13) {
            for (int i = 0; i < books.size(); i++) {
                if (ISBN13.equals(books.get(i).getISBN13())) {
                    books.get(i).lendBook();
                    break;
                }
            }
        }

        public void returnBook(String ISBN13) {
            for (int i = 0; i < books.size(); i++) {
                if (ISBN13.equals(books.get(i).getISBN13())) {
                    books.get(i).returnBook();
                    break;
                }
            }
        }

        public Book getBookFromISBN(String ISBN13) {
            for (Book book : books) {
                if (book.getISBN13().equals(ISBN13)) {
                    return book;
                }
            }
            for (Book book : eBooks) {
                if (book.getISBN13().equals(ISBN13)) {
                    return book;
                }
            }
            throw new IllegalArgumentException("Não existem correspondências na biblioteca para este ISBN");
        }
}
