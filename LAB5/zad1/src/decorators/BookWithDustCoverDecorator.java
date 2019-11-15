package decorators;

import interfaces.Publication;

public class BookWithDustCoverDecorator extends BookDecorator {
    public BookWithDustCoverDecorator(Publication book) {
        super(book);
        this.listOfClasses = book.getListOfClasses();
        if(book.contains(this.getClass())) {
            throw new IllegalArgumentException("Book already has dust cover");
        }
        else if(!book.contains(BookWithSoftCoverDecorator.class) && !book.contains(BookWithHardCoverDecorator.class)) {
            throw new IllegalArgumentException("Book without cover can't have dust cover");
        }
        this.addClass(this.getClass());
    }

    @Override
    public String toString() {
        return book.toString() + " Obwoluta |";
    }
}
