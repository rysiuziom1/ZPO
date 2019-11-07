package decorators;

import interfaces.Publication;

public abstract class BookDecorator implements Publication{
    Publication book;

    BookDecorator(Publication book) {
        this.book = book;
    }

    @Override
    public String getAuthor() {
        return book.getAuthor();
    }

    @Override
    public String getTitle() {
        return book.getTitle();
    }

    @Override
    public int getPages() {
        return book.getPages();
    }
}
