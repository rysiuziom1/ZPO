package decorators;

import interfaces.Publication;

public class BookWithHardCoverDecorator extends BookDecorator {
    public BookWithHardCoverDecorator(Publication book) {
        super(book);
        this.listOfClasses = book.getListOfClasses();
        if(book.contains(this.getClass()) || book.contains(BookWithSoftCoverDecorator.class)) {
            throw new IllegalArgumentException("Book already has cover");
        }
        this.addClass(this.getClass());
    }

    @Override
    public String toString() {
        return book.toString() + " Okładka twarda |";
    }
}
