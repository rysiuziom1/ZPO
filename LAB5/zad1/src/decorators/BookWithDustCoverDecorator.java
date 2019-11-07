package decorators;

import interfaces.Publication;

public class BookWithDustCoverDecorator extends BookDecorator {
    public BookWithDustCoverDecorator(Publication book) {
        super(book);
        if(book.getClass() == this.getClass())
            throw new IllegalArgumentException("books.Book already has dust cover");
        else if(book.getClass() != BookWithSoftCoverDecorator.class && book.getClass() != BookWithHardCoverDecorator.class)
            throw new IllegalArgumentException("books.Book without cover can't have dust cover");
    }

    @Override
    public String toString() {
        return book.toString() + " Obwoluta |";
    }
}
