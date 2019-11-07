package abstractfactory;

import books.Book;
import books.Poem;

public class PublisherPoem extends Publisher {
    private String author;

    public PublisherPoem(String author) {
        this.author = author;
    }

    @Override
    public Book createBook(String title, int pages) {
        return new Poem(new Book(author, title, pages));
    }
}
