package decorators;

import interfaces.Publication;

public class BookWithSoftCoverDecorator extends BookDecorator {
    public BookWithSoftCoverDecorator(Publication book) {
        super(book);
        if(book.getClass() == this.getClass() || book.getClass() == BookWithHardCoverDecorator.class)
            throw new IllegalArgumentException("books.Book already has cover");
    }

    @Override
    public String toString() {
        return book.toString() + " Okładka miękka |";
    }
}
