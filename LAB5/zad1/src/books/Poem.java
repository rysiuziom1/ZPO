package books;

public class Poem extends Book {
    public Poem(Book book) {
        super(book.getAuthor(), book.getTitle(), book.getPages());
    }
}
