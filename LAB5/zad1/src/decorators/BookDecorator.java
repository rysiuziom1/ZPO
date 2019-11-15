package decorators;

import interfaces.Publication;

import java.util.ArrayList;
import java.util.List;

public abstract class BookDecorator implements Publication {
    Publication book;
    List<Class> listOfClasses = new ArrayList<>();

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

    @Override
    public boolean contains(Class c) {
        return listOfClasses.contains(c);
    }

    @Override
    public void addClass(Class c) {
        listOfClasses.add(c);
    }

    @Override
    public List<Class> getListOfClasses() {
        return new ArrayList<>(listOfClasses);
    }
}
