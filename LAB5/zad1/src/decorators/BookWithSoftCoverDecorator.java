package decorators;

import interfaces.Publication;

public class BookWithSoftCoverDecorator extends BookDecorator {
    public BookWithSoftCoverDecorator(Publication book) {
        super(book);
        this.listOfClasses = book.getListOfClasses();
        if(book.contains(this.getClass()) || book.contains(BookWithHardCoverDecorator.class)) {
            throw new IllegalArgumentException("Book already has cover");
        }
        this.addClass(this.getClass());
    }

    @Override
    public String toString() {
        return book.toString() + " Okładka miękka |";
    }
}
