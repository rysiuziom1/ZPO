package books;

public class Thriller extends Book {
    public Thriller(Book book) {
        super(book.getAuthor(), book.getTitle(), book.getPages());
    }
}
