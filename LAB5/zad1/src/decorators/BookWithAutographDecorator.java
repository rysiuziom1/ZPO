package decorators;

import interfaces.Publication;

public class BookWithAutographDecorator extends BookDecorator {
    private String autograph;

    public BookWithAutographDecorator(Publication book, String autograph) {
        super(book);
        this.listOfClasses = book.getListOfClasses();
        if (book.contains(this.getClass())) {
            throw new IllegalArgumentException("Book already has autograph");
        }
        this.autograph = autograph;
        this.addClass(this.getClass());
    }

    @Override
    public String toString() {
        return book.toString() + " " + autograph + " |";
    }
}
