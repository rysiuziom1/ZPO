package abstractfactory;

import books.Book;
import books.HistoricalNovel;

public class PublisherHistoricalNovel extends Publisher {

    private String author;

    public PublisherHistoricalNovel(String author) {
        this.author = author;
    }

    @Override
    public Book createBook(String title, int pages) {
        return new HistoricalNovel(new Book(author, title, pages));
    }
}
