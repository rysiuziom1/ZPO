package books;

public class HistoricalNovel extends Book {
    public HistoricalNovel(Book book) {
        super(book.getAuthor(), book.getTitle(), book.getPages());
    }
}
