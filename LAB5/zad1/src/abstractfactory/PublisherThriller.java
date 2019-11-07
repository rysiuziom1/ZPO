package abstractfactory;

import books.Book;
import books.Thriller;

public class PublisherThriller extends Publisher {
    private String author;

    public PublisherThriller(String author) {
        this.author = author;
    }

    @Override
    public Book createBook(String title, int pages) {
        return new Thriller(new Book(author, title, pages));
    }
}
