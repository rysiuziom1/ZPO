package decorators;

import interfaces.Publication;

public class BookWithHardCoverDecorator extends BookDecorator {
    public BookWithHardCoverDecorator(Publication book) {
        super(book);
        if(book.getClass() == this.getClass() || book.getClass() == BookWithSoftCoverDecorator.class) {
            throw new IllegalArgumentException("books.Book already has cover");
        }
    }

    @Override
    public String toString() {
        return book.toString() + " Okładka twarda |";
    }
}
