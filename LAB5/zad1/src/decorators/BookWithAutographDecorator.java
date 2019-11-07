package decorators;

import interfaces.Publication;

public class BookWithAutographDecorator extends BookDecorator {
    private String autograph;

    public BookWithAutographDecorator(Publication book, String autograph) {
        super(book);
        if (book.getClass() == this.getClass())
            throw new IllegalArgumentException("books.Book already has autograph");
        this.autograph = autograph;
    }

    @Override
    public String toString() {
        return book.toString() + " " + autograph + " |";
    }
}
