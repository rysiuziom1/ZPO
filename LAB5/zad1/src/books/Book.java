package books;

import interfaces.Publication;

import java.util.ArrayList;
import java.util.List;

public class Book implements Publication {
    private String author;
    private String title;
    private int pages;


    public Book(String author, String title, int pages) {
        this.author = author;
        this.title = title;
        this.pages = pages;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getPages() {
        return pages;
    }

    @Override
    public boolean contains(Class c) {
        return false;
    }

    @Override
    public void addClass(Class c) {

    }

    @Override
    public List<Class> getListOfClasses() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "| " + author + " | " + title + " | " + pages + " |";
    }
}
